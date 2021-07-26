import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DictionaryData } from 'src/app/shared/types/DictionaryData';
import { Model } from 'src/app/shared/types/Model';
import { ManufacturerService } from 'src/app/services/rest/manufacturer.service';
import { ModelService } from 'src/app/services/rest/model.service';
import { DictionaryFormDialogComponent } from '../dictionary-form-dialog/dictionary-form-dialog.component';
import { DeleteGenericDialogComponent } from '../delete-generic-dialog/delete-generic-dialog.component';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-model-form-dialog',
  templateUrl: './model-form-dialog.component.html',
  styleUrls: ['./model-form-dialog.component.scss']
})
export class ModelFormDialogComponent implements OnInit {
  private _manufacturers!: DictionaryData[];
  public get manufacturers(): DictionaryData[] {
    return this._manufacturers;
  }

  private _manufacturerId?: number = undefined;
  public get manufacturerId(): number | undefined {
    return this._manufacturerId;
  }
  public set manufacturerId(value: number | undefined) {
    this._manufacturerId = value;
  }

  private _name?: string = undefined;
  public get name(): string | undefined {
    return this._name;
  }
  public set name(value: string | undefined) {
    this._name = value;
  }

  constructor(private _modelService: ModelService,
    private _manufacturerService: ManufacturerService,
    private _dialogRef: MatDialogRef<ModelFormDialogComponent>,
    private _dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) readonly editId?: number,
  ) { }

  ngOnInit(): void {
    this.updateManufacturerList();
    if (this.editId) {
      this._modelService.getById(this.editId).subscribe(
        (response: Model) => {
          this.manufacturerId = response.manufacturer.id;
          this.name = response.name;
        }
      )
    }
  }

  updateManufacturerList(): void {
    this._manufacturerService.getAll().subscribe(
      (response: DictionaryData[]) => {
        this._manufacturers = response;
      }
    )
  }

  openManufacturerFormDialog(prefs: {edit: boolean}): void {
    let dialogRef;
    if (prefs.edit) {
      dialogRef = this._dialog.open(DictionaryFormDialogComponent, {data: {service: this._manufacturerService, editId: this.manufacturerId}});
    } else {
      dialogRef = this._dialog.open(DictionaryFormDialogComponent, {data: {service: this._manufacturerService}});
    }
    dialogRef.afterClosed().subscribe(
      (response) => {
        if (response && response.updateList) {
          this.updateManufacturerList();
        }
      }
    )
  }

  openDeleteManufacturerDialog(): void {
    const dialogRef = this._dialog.open(DeleteGenericDialogComponent, {data: {service: this._manufacturerService, id: this.manufacturerId}});
    dialogRef.afterClosed().subscribe(
      (response) => {
        if (response && response.updateList) {
          this.updateManufacturerList();
        }
      }
    )
  }

  processForm(): void {
    if (this.manufacturerId && this.name) {
      let serviceResult;
      if (this.editId) {
        serviceResult = this._doEdit();
      } else {
        serviceResult = this._doAdd();
      }
      serviceResult.subscribe(
        () => {
          this._dialogRef.close({updateList: true});
        }
      )
    }
  }

  private _doEdit(): Observable<Model> {
    return this._modelService.update({ 
      id: this.editId, 
      manufacturer: { 
        id: this.manufacturerId 
      }, 
      name: this.name 
    } as Model);
  }

  private _doAdd(): Observable<Model> {
    return  this._modelService.add({ 
      manufacturerId: this.manufacturerId!, 
      name: this.name! 
    });
  }

  closeDialog(updateList: boolean): void {
    this._dialogRef.close({updateList: updateList});
  }
}
