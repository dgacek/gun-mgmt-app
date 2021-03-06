import { Component } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { GunFormDialogComponent } from 'src/app/views/components/dialogs/gun-form-dialog/gun-form-dialog.component';
import { DeleteGenericDialogComponent } from 'src/app/views/components/dialogs/delete-generic-dialog/delete-generic-dialog.component';
import { Gun } from 'src/app/shared/types/Gun';
import { GunService } from 'src/app/services/rest/gun.service';

@Component({
  selector: 'app-gunlist-page',
  templateUrl: './gunlist-page.component.html',
  styleUrls: ['./gunlist-page.component.scss']
})
export class GunlistPageComponent {
  private _selectedItem?: Gun = undefined;
  public get selectedItem(): Gun | undefined {
    return this._selectedItem;
  }
  public set selectedItem(value: Gun | undefined) {
    this._selectedItem = value;
  }

  private _viewUpdater: boolean = false;
  public get viewUpdater(): boolean {
    return this._viewUpdater;
  }
  public set viewUpdater(value: boolean) {
    this._viewUpdater = value;
  }

  constructor(private _gunService: GunService,
    private _dialog: MatDialog) { }

  setSelectedItem(item: Gun) {
    this.selectedItem = item;
  }

  openGunFormDialog(prefs: { edit: boolean }): void {
    let dialogRef;
    if (prefs.edit) {
      dialogRef = this._openEditGunDialog();
    } else {
      dialogRef = this._openAddGunDialog();
    }
    dialogRef.afterClosed().subscribe(this._handleGunDialogClose.bind(this));
  }

  openDeleteGunDialog(): void {
    const dialogRef = this._dialog.open(DeleteGenericDialogComponent, { data: { service: this._gunService, id: this.selectedItem?.id } });
    dialogRef.afterClosed().subscribe(this._handleGunDialogClose.bind(this))
  }

  private _openEditGunDialog(): MatDialogRef<any> {
    return this._dialog.open(GunFormDialogComponent, { data: this.selectedItem?.id });
  }

  private _openAddGunDialog(): MatDialogRef<any> {
    return this._dialog.open(GunFormDialogComponent);
  }

  private _handleGunDialogClose(response: any): void {
    if (response && response.updateList) {
      this.updateView();
    }
  }

  /**
   * Signals the child GunlistComponent to update the list, utilizes child's ngOnChanges()
   */
  updateView(): void {
    this.viewUpdater = !this.viewUpdater;
  }

}
