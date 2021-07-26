import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { BasicCRUDService } from 'src/app/shared/types/BasicCRUDService';

@Component({
  selector: 'app-delete-generic-dialog',
  templateUrl: './delete-generic-dialog.component.html',
  styleUrls: ['./delete-generic-dialog.component.scss']
})
export class DeleteGenericDialogComponent {

  constructor(
    private _dialogRef: MatDialogRef<DeleteGenericDialogComponent>,
    @Inject(MAT_DIALOG_DATA) readonly prefs: { service: BasicCRUDService, id: number }
  ) { }


  doDelete(): void {
    this.prefs.service.delete(this.prefs.id).subscribe(
      () => {
        this._dialogRef.close({ updateList: true })
      }
    )
  }

  closeDialog(updateList: boolean): void {
    this._dialogRef.close({ updateList: updateList });
  }

}
