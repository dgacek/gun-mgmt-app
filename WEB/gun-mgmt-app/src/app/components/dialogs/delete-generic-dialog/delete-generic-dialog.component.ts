import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-delete-generic-dialog',
  templateUrl: './delete-generic-dialog.component.html',
  styleUrls: ['./delete-generic-dialog.component.scss']
})
export class DeleteGenericDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<DeleteGenericDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public prefs: { serviceMethodCallback: (id: number) => Observable<void>, id: number }
  ) { }

  doDelete(): void {
    this.prefs.serviceMethodCallback(this.prefs.id).subscribe(
      () => {
        this.dialogRef.close({ updateList: true })
      }
    )
  }

}
