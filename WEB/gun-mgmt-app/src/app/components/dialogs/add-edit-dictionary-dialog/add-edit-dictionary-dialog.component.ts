import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { BasicCRUDService } from 'src/app/types/BasicCRUDService';
import { DictionaryData } from 'src/app/types/DictionaryData';

@Component({
  selector: 'app-add-edit-dictionary-dialog',
  templateUrl: './add-edit-dictionary-dialog.component.html',
  styleUrls: ['./add-edit-dictionary-dialog.component.scss']
})
export class AddEditDictionaryDialogComponent implements OnInit {
  name?: string = undefined;

  constructor(
    public dialogRef: MatDialogRef<AddEditDictionaryDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public prefs: { service: BasicCRUDService, editId?: number }
  ) { }

  ngOnInit(): void {
    if (this.prefs.editId) {
      this.prefs.service.getById(this.prefs.editId).subscribe(
        (response: DictionaryData) => {
          this.name = response.name;
        }
      )
    }
  }

  doAddOrEdit(): void {
    if (this.name) {
      let serviceResult;
      if (this.prefs.editId) {
        serviceResult = this.prefs.service.update({ id: this.prefs.editId, name: this.name });
      } else {
        serviceResult = this.prefs.service.add({ name: this.name });
      }
      serviceResult.subscribe(
        () => {
          this.dialogRef.close({ updateList: true });
        }
      )
    }
  }
}