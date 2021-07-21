import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DictionaryData } from 'src/app/models/DictionaryData';
import { Model } from 'src/app/models/Model';
import { ManufacturerService } from 'src/app/services/manufacturer.service';
import { ModelService } from 'src/app/services/model.service';
import { AddEditDictionaryDialogComponent } from '../add-edit-dictionary-dialog/add-edit-dictionary-dialog.component';
import { DeleteGenericDialogComponent } from '../delete-generic-dialog/delete-generic-dialog.component';

@Component({
  selector: 'app-add-edit-model-dialog',
  templateUrl: './add-edit-model-dialog.component.html',
  styleUrls: ['./add-edit-model-dialog.component.scss']
})
export class AddEditModelDialogComponent implements OnInit {
  manufacturers?: DictionaryData[] = undefined;
  manufacturerId?: number = undefined;
  name?: string = undefined;

  constructor(private modelService: ModelService,
    private manufacturerService: ManufacturerService,
    public dialogRef: MatDialogRef<AddEditModelDialogComponent>,
    public dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public editId?: number,
  ) { }

  ngOnInit(): void {
    this.updateManufacturerList();
    if (this.editId) {
      this.modelService.getModelById(this.editId).subscribe(
        (response: Model) => {
          this.manufacturerId = response.manufacturer.id;
          this.name = response.name;
        }
      )
    }
  }

  updateManufacturerList(): void {
    this.manufacturerService.getAllManufacturers().subscribe(
      (response: DictionaryData[]) => {
        this.manufacturers = response;
      }
    )
  }

  openAddEditManufacturerDialog(prefs: {edit: boolean}): void {
    let dialogRef;
    if (prefs.edit) {
      dialogRef = this.dialog.open(AddEditDictionaryDialogComponent, {data: {mode: "manufacturer", editId: this.manufacturerId}});
    } else {
      dialogRef = this.dialog.open(AddEditDictionaryDialogComponent, {data: {mode: "manufacturer"}});
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
    const dialogRef = this.dialog.open(DeleteGenericDialogComponent, {data: {serviceMethodCallback: this.manufacturerService.deleteManufacturer.bind(this.manufacturerService), id: this.manufacturerId}});
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
        this.modelService.updateModel({ id: this.editId, manufacturer: { id: this.manufacturerId }, name: this.name } as Model).subscribe(
          () => {
            this.dialogRef.close({ updateList: true });
          }
        )
      } else {
        this.modelService.addModel({ manufacturerId: this.manufacturerId, name: this.name }).subscribe(
          () => {
            this.dialogRef.close({ updateList: true });
          }
        )
      }
    }
  }

}
