import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { BasicCRUDService } from 'src/app/types/BasicCRUDService';
import { DictionaryData } from 'src/app/types/DictionaryData';

@Component({
  selector: 'app-dictionary-form-dialog',
  templateUrl: './dictionary-form-dialog.component.html',
  styleUrls: ['./dictionary-form-dialog.component.scss']
})
export class DictionaryFormDialogComponent implements OnInit {
  private _name?: string = undefined;
  public get name(): (string | undefined) {
    return this._name;
  }
  public set name(value: string | undefined) {
    this._name = value;
  }

  constructor(
    private _dialogRef: MatDialogRef<DictionaryFormDialogComponent>,
    @Inject(MAT_DIALOG_DATA) readonly prefs: { service: BasicCRUDService, editId?: number }
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

  processForm(): void {
    if (this._name) {
      let serviceResult;
      if (this.prefs.editId) {
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

  closeDialog(updateList: boolean): void {
    this._dialogRef.close({updateList: updateList});
  }
  
  private _doAdd(): Observable<DictionaryData> {
    return this.prefs.service.add({name: this._name});
  }

  private _doEdit(): Observable<DictionaryData> {
    return this.prefs.service.update({ id: this.prefs.editId, name: this._name });
  }
}
