import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DictionaryData } from 'src/app/types/DictionaryData';
import { Model } from 'src/app/types/Model';
import { ManufacturerService } from 'src/app/services/rest/manufacturer.service';
import { ModelService } from 'src/app/services/rest/model.service';
import { DictionaryFormDialogComponent } from '../dictionary-form-dialog/dictionary-form-dialog.component';
import { DeleteGenericDialogComponent } from '../delete-generic-dialog/delete-generic-dialog.component';

@Component({
  selector: 'app-model-form-dialog',
  templateUrl: './model-form-dialog.component.html',
  styleUrls: ['./model-form-dialog.component.scss']
})
export class ModelFormDialogComponent implements OnInit {
  manufacturers?: DictionaryData[] = undefined;
  manufacturerId?: number = undefined;
  name?: string = undefined;

  constructor(private modelService: ModelService,
    private manufacturerService: ManufacturerService,
    public dialogRef: MatDialogRef<ModelFormDialogComponent>,
    public dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public editId?: number,
  ) { }

  ngOnInit(): void {
    this.updateManufacturerList();
    if (this.editId) {
      this.modelService.getById(this.editId).subscribe(
        (response: Model) => {
          this.manufacturerId = response.manufacturer.id;
          this.name = response.name;
        }
      )
    }
  }

  updateManufacturerList(): void {
    this.manufacturerService.getAll().subscribe(
      (response: DictionaryData[]) => {
        this.manufacturers = response;
      }
    )
  }

  openAddEditManufacturerDialog(prefs: {edit: boolean}): void {
    let dialogRef;
    if (prefs.edit) {
      dialogRef = this.dialog.open(DictionaryFormDialogComponent, {data: {service: this.manufacturerService, editId: this.manufacturerId}});
    } else {
      dialogRef = this.dialog.open(DictionaryFormDialogComponent, {data: {service: this.manufacturerService}});
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
    const dialogRef = this.dialog.open(DeleteGenericDialogComponent, {data: {service: this.manufacturerService, id: this.manufacturerId}});
    dialogRef.afterClosed().subscribe(
      (response) => {
        if (response && response.updateList) {
          this.updateManufacturerList();
        }
      }
    )
  }

  doAddOrEdit(): void {
    if (this.manufacturerId && this.name) {
      if (this.editId) {
        this.modelService.update({ id: this.editId, manufacturer: { id: this.manufacturerId }, name: this.name } as Model).subscribe(
          () => {
            this.dialogRef.close({ updateList: true });
          }
        )
      } else {
        this.modelService.add({ manufacturerId: this.manufacturerId, name: this.name }).subscribe(
          () => {
            this.dialogRef.close({ updateList: true });
          }
        )
      }
    }
  }

}
