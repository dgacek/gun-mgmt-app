import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DictionaryData } from 'src/app/types/DictionaryData';
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
          serviceResult = this.manufacturerService.getById(this.prefs.editId);
          break;
        case "caliber":
          serviceResult = this.caliberService.getById(this.prefs.editId);
          break;
        case "type":
          serviceResult = this.typeService.getById(this.prefs.editId);
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
            serviceResult = this.manufacturerService.update({ id: this.prefs.editId, name: this.name });
            break;
          case "caliber":
            serviceResult = this.caliberService.update({ id: this.prefs.editId, name: this.name });
            break;
          case "type":
            serviceResult = this.typeService.update({ id: this.prefs.editId, name: this.name });
            break;
        }
      } else {
        switch (this.prefs.mode) {
          case "manufacturer":
            serviceResult = this.manufacturerService.add({ name: this.name });
            break;
          case "caliber":
            serviceResult = this.caliberService.add({ name: this.name });
            break;
          case "type":
            serviceResult = this.typeService.add({ name: this.name });
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
