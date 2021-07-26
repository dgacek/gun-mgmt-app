import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { GunFormDialogComponent } from 'src/app/components/dialogs/gun-form-dialog/gun-form-dialog.component';
import { DeleteGenericDialogComponent } from 'src/app/components/dialogs/delete-generic-dialog/delete-generic-dialog.component';
import { Gun } from 'src/app/types/Gun';
import { GunService } from 'src/app/services/rest/gun.service';

@Component({
  selector: 'app-gunlist-page',
  templateUrl: './gunlist-page.component.html',
  styleUrls: ['./gunlist-page.component.scss']
})
export class GunlistPageComponent {
  selectedItem?: Gun = undefined;
  viewUpdater: boolean = false;

  constructor( private gunService: GunService,
    public dialog: MatDialog) { }

  setSelectedItem(item: Gun) {
    this.selectedItem = item;
  }

  openAddEditGunDialog(prefs: {edit: boolean}): void {
    let dialogRef;
    if (prefs.edit) {
      dialogRef = this.dialog.open(GunFormDialogComponent, { data: this.selectedItem ? this.selectedItem.id : null });
    } else {
      dialogRef = this.dialog.open(GunFormDialogComponent);
    }
    dialogRef.afterClosed().subscribe(
      (response) => {
        if (response && response.updateList) {
          this.updateView();
        }
      }
    )
  }

  openDeleteGunDialog(): void {
    const dialogRef = this.dialog.open(DeleteGenericDialogComponent, {data: {service: this.gunService, id: this.selectedItem ? this.selectedItem.id : null}});
    dialogRef.afterClosed().subscribe(
      (response) => {
        if (response && response.updateList) {
          this.updateView();
        }
      }
    )
  }

  /**
   * Signals the child GunlistComponent to update the list, utilizes child's ngOnChanges()
   */
  updateView(): void {
    this.viewUpdater = !this.viewUpdater;
  }

}
