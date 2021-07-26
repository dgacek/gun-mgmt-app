import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DictionaryData } from 'src/app/shared/types/DictionaryData';
import { Gun } from 'src/app/shared/types/Gun';
import { Model } from 'src/app/shared/types/Model';
import { CaliberService } from 'src/app/services/rest/caliber.service';
import { GunService } from 'src/app/services/rest/gun.service';
import { ModelService } from 'src/app/services/rest/model.service';
import { TypeService } from 'src/app/services/rest/type.service';
import { DictionaryFormDialogComponent } from '../dictionary-form-dialog/dictionary-form-dialog.component';
import { ModelFormDialogComponent } from '../model-form-dialog/model-form-dialog.component';
import { DeleteGenericDialogComponent } from '../delete-generic-dialog/delete-generic-dialog.component';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-gun-form-dialog',
  templateUrl: './gun-form-dialog.component.html',
  styleUrls: ['./gun-form-dialog.component.scss']
})
export class GunFormDialogComponent implements OnInit {
  private _models!: Model[];
  public get models(): Model[] {
    return this._models;
  }

  private _calibers!: DictionaryData[];
  public get calibers(): DictionaryData[] {
    return this._calibers;
  }

  private _types!: DictionaryData[];
  public get types(): DictionaryData[] {
    return this._types;
  }

  private _modelId?: number = undefined;
  public get modelId(): number | undefined {
    return this._modelId;
  }
  public set modelId(value: number | undefined) {
    this._modelId = value;
  }

  private _caliberId?: number = undefined;
  public get caliberId(): number | undefined {
    return this._caliberId;
  }
  public set caliberId(value: number | undefined) {
    this._caliberId = value;
  }

  private _typeId?: number = undefined;
  public get typeId(): number | undefined {
    return this._typeId;
  }
  public set typeId(value: number | undefined) {
    this._typeId = value;
  }

  private _productionYear?: number = undefined;
  public get productionYear(): number | undefined {
    return this._productionYear;
  }
  public set productionYear(value: number | undefined) {
    this._productionYear = value;
  }

  constructor(private _modelService: ModelService,
    private _caliberService: CaliberService,
    private _typeService: TypeService,
    private _gunService: GunService,
    private _dialogRef: MatDialogRef<GunFormDialogComponent>,
    private _dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) readonly editId?: number
  ) { }

  ngOnInit(): void {
    this.updateModelList();
    this.updateCaliberList();
    this.updateTypeList();
    if (this.editId) {
      this._gunService.getById(this.editId).subscribe(
        (response: Gun) => {
          this.modelId = response.model.id;
          this.caliberId = response.caliber.id;
          this.typeId = response.type.id;
          this.productionYear = response.productionYear;
        }
      )
    }
  }

  updateModelList(): void {
    this._modelService.getAll().subscribe(
      (response: Model[]) => {
        this._models = response;
      }
    )
  }

  updateCaliberList(): void {
    this._caliberService.getAll().subscribe(
      (response: DictionaryData[]) => {
        this._calibers = response;
      }
    )
  }

  updateTypeList(): void {
    this._typeService.getAll().subscribe(
      (response: DictionaryData[]) => {
        this._types = response;
      }
    )
  }

  openModelFormDialog(prefs: { edit: boolean }): void {
    let dialogRef;
    if (prefs.edit) {
      dialogRef = this._openEditModelDialog();
    } else {
      dialogRef = this._openAddModelDialog();
    }
    dialogRef.afterClosed().subscribe(this._handleModelDialogClose.bind(this));
  }

  openDeleteModelDialog(): void {
    const dialogRef = this._dialog.open(DeleteGenericDialogComponent, { data: { service: this._modelService, id: this.modelId } });
    dialogRef.afterClosed().subscribe(this._handleModelDialogClose.bind(this));
  }

  private _openEditModelDialog(): MatDialogRef<any> {
    return this._dialog.open(ModelFormDialogComponent, { data: this.modelId });
  }

  private _openAddModelDialog(): MatDialogRef<any> {
    return this._dialog.open(ModelFormDialogComponent);
  }

  private _handleModelDialogClose(response: any): void {
    if (response && response.updateList) {
      this.updateModelList();
    }
  }

  openCaliberFormDialog(prefs: { edit: boolean }): void {
    let dialogRef;
    if (prefs.edit) {
      dialogRef = this._openEditCaliberDialog();
    } else {
      dialogRef = this._openAddCaliberDialog();
    }
    dialogRef.afterClosed().subscribe(this._handleCaliberDialogClose.bind(this));
  }

  openDeleteCaliberDialog(): void {
    const dialogRef = this._dialog.open(DeleteGenericDialogComponent, { data: { service: this._caliberService, id: this.caliberId } });
    dialogRef.afterClosed().subscribe(this._handleCaliberDialogClose.bind(this));
  }

  private _openEditCaliberDialog(): MatDialogRef<any> {
    return this._dialog.open(DictionaryFormDialogComponent, { data: { service: this._caliberService, editId: this.caliberId } });
  }

  private _openAddCaliberDialog(): MatDialogRef<any> {
    return this._dialog.open(DictionaryFormDialogComponent, { data: { service: this._caliberService } });
  }

  private _handleCaliberDialogClose(response: any): void {
    if (response && response.updateList) {
      this.updateCaliberList();
    }
  }

  openTypeFormDialog(prefs: { edit: boolean }): void {
    let dialogRef;
    if (prefs.edit) {
      dialogRef = this._openEditTypeDialog();
    } else {
      dialogRef = this._openAddTypeDialog();
    }
    dialogRef.afterClosed().subscribe(this._handleTypeDialogClose.bind(this));
  }

  openDeleteTypeDialog(): void {
    const dialogRef = this._dialog.open(DeleteGenericDialogComponent, { data: { service: this._typeService, id: this.typeId } });
    dialogRef.afterClosed().subscribe(this._handleTypeDialogClose.bind(this));
  }

  private _openEditTypeDialog(): MatDialogRef<any> {
    return this._dialog.open(DictionaryFormDialogComponent, { data: { service: this._typeService, editId: this.typeId } });
  }

  private _openAddTypeDialog(): MatDialogRef<any> {
    return this._dialog.open(DictionaryFormDialogComponent, { data: { service: this._typeService } });
  }

  private _handleTypeDialogClose(response: any): void {
    if (response && response.updateList) {
      this.updateTypeList();
    }
  }

  processForm(): void {
    if (this.modelId && this.caliberId && this.typeId && this.productionYear) {
      let serviceResult;
      if (this.editId) {
        serviceResult = this._doEdit();
      } else {
        serviceResult = this._doAdd();
      }
      serviceResult.subscribe(
        () => {
          this._dialogRef.close({ updateList: true });
        }
      )
    }
  }

  private _doEdit(): Observable<Gun> {
    return this._gunService.update({
      id: this.editId?.valueOf(),
      model: {
        id: this.modelId
      },
      caliber: {
        id: this.caliberId
      },
      type: {
        id: this.typeId
      },
      productionYear: this.productionYear
    } as Gun)
  }

  private _doAdd(): Observable<Gun> {
    return this._gunService.add({
      modelId: this.modelId!,
      caliberId: this.caliberId!,
      typeId: this.typeId!,
      productionYear: this.productionYear!
    })
  }

  closeDialog(updateList: boolean): void {
    this._dialogRef.close({ updateList: updateList });
  }
}
