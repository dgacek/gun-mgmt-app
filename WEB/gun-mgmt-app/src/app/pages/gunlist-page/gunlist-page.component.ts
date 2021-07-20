import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddEditGunDialogComponent } from 'src/app/components/dialogs/add-edit-gun-dialog/add-edit-gun-dialog.component';
import { Gun } from 'src/app/models/Gun';

@Component({
  selector: 'app-gunlist-page',
  templateUrl: './gunlist-page.component.html',
  styleUrls: ['./gunlist-page.component.scss']
})
export class GunlistPageComponent implements OnInit {
  selectedItem?: Gun = undefined;
  viewUpdater: boolean = false;

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  setSelectedItem(item: Gun) {
    this.selectedItem = item;
    console.log(item);
  }

  openAddEditGunDialog(prefs: {edit: boolean}): void {
    let dialogRef;
    if (prefs.edit) {
      dialogRef = this.dialog.open(AddEditGunDialogComponent, { data: this.selectedItem ? this.selectedItem.id : null });
    } else {
      dialogRef = this.dialog.open(AddEditGunDialogComponent);
    }
    dialogRef.afterClosed().subscribe(
      (response) => {
        if (response.updateList) {
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
