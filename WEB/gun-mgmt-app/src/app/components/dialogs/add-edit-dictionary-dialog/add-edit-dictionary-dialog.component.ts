import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DictionaryData } from 'src/app/models/DictionaryData';
import { CaliberService } from 'src/app/services/caliber.service';
import { ManufacturerService } from 'src/app/services/manufacturer.service';
import { TypeService } from 'src/app/services/type.service';

@Component({
  selector: 'app-add-edit-dictionary-dialog',
  templateUrl: './add-edit-dictionary-dialog.component.html',
  styleUrls: ['./add-edit-dictionary-dialog.component.scss']
})
export class AddEditDictionaryDialogComponent implements OnInit {
  name?: string = undefined;

  constructor(private manufacturerService: ManufacturerService,
    private caliberService: CaliberService,
    private typeService: TypeService,
    public dialogRef: MatDialogRef<AddEditDictionaryDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public prefs: { mode: string, editId?: number }
  ) { }

  ngOnInit(): void {
    if (this.prefs.editId) {
      let serviceResult;
      switch (this.prefs.mode) {
        case "manufacturer":
          serviceResult = this.manufacturerService.getManufacturerById(this.prefs.editId);
          break;
        case "caliber":
          serviceResult = this.caliberService.getCaliberById(this.prefs.editId);
          break;
        case "type":
          serviceResult = this.typeService.getTypeById(this.prefs.editId);
          break;
      }
      if (serviceResult) {
        serviceResult.subscribe(
          (response: DictionaryData) => {
            this.name = response.name;
          }
        )
      }
    }
  }

  doAddOrEdit(): void {
    if (this.name) {
      let serviceResult;
      if (this.prefs.editId) {
        switch (this.prefs.mode) {
          case "manufacturer":
            serviceResult = this.manufacturerService.updateManufacturer({ id: this.prefs.editId, name: this.name });
            break;
          case "caliber":
            serviceResult = this.caliberService.updateCaliber({ id: this.prefs.editId, name: this.name });
            break;
          case "type":
            serviceResult = this.typeService.updateType({ id: this.prefs.editId, name: this.name });
            break;
        }
      } else {
        switch (this.prefs.mode) {
          case "manufacturer":
            serviceResult = this.manufacturerService.addManufacturer({ name: this.name });
            break;
          case "caliber":
            serviceResult = this.caliberService.addCaliber({ name: this.name });
            break;
          case "type":
            serviceResult = this.typeService.addType({ name: this.name });
            break;
        }
      }
      if (serviceResult) {
        serviceResult.subscribe(
          () => {
            this.dialogRef.close({ updateList: true });
          }
        )
      }
    }
  }
}
