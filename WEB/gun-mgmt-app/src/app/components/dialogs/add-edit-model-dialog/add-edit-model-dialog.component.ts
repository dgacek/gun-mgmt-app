import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DictionaryData } from 'src/app/models/DictionaryData';
import { Model } from 'src/app/models/Model';
import { ManufacturerService } from 'src/app/services/manufacturer.service';
import { ModelService } from 'src/app/services/model.service';

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
    @Inject(MAT_DIALOG_DATA) public editId?: number
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
