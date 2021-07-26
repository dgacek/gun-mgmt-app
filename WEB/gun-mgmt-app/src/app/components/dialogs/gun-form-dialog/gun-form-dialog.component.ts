import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DictionaryData } from 'src/app/types/DictionaryData';
import { Gun } from 'src/app/types/Gun';
import { Model } from 'src/app/types/Model';
import { CaliberService } from 'src/app/services/rest/caliber.service';
import { GunService } from 'src/app/services/rest/gun.service';
import { ModelService } from 'src/app/services/rest/model.service';
import { TypeService } from 'src/app/services/rest/type.service';
import { DictionaryFormDialogComponent } from '../dictionary-form-dialog/dictionary-form-dialog.component';
import { ModelFormDialogComponent } from '../model-form-dialog/model-form-dialog.component';
import { DeleteGenericDialogComponent } from '../delete-generic-dialog/delete-generic-dialog.component';

@Component({
  selector: 'app-gun-form-dialog',
  templateUrl: './gun-form-dialog.component.html',
  styleUrls: ['./gun-form-dialog.component.scss']
})
export class GunFormDialogComponent implements OnInit {
  models?: Model[] = undefined;
  calibers?: DictionaryData[] = undefined;
  types?: DictionaryData[] = undefined;
  modelId?: number = undefined;
  caliberId?: number = undefined;
  typeId?: number = undefined;
  productionYear?: number = undefined;

  constructor(private modelService: ModelService,
    private caliberService: CaliberService,
    private typeService: TypeService,
    private gunService: GunService,
    public dialogRef: MatDialogRef<GunFormDialogComponent>,
    public dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public editId?: number
  ) { }

  ngOnInit(): void {
    this.updateModelList();
    this.updateCaliberList();
    this.updateTypeList();
    if (this.editId) {
      this.gunService.getById(this.editId).subscribe(
        (response: Gun) => {
          this.modelId = response.model.id;
          this.caliberId = response.caliber.id;
          this.typeId = response.type.id;
          this.productionYear = response.productionYear;
        }
      )
    }
  }

  updateModelList(): void {
    this.modelService.getAll().subscribe(
      (response: Model[]) => {
        this.models = response;
      }
    )
  }

  updateCaliberList(): void {
    this.caliberService.getAll().subscribe(
      (response: DictionaryData[]) => {
        this.calibers = response;
      }
    )
  }

  updateTypeList(): void {
    this.typeService.getAll().subscribe(
      (response: DictionaryData[]) => {
        this.types = response;
      }
    )
  }

  openAddEditModelDialog(prefs: { edit: boolean }): void {
    let dialogRef;
    if (prefs.edit) {
      dialogRef = this.dialog.open(ModelFormDialogComponent, { data: this.modelId });
    } else {
      dialogRef = this.dialog.open(ModelFormDialogComponent);
    }
    dialogRef.afterClosed().subscribe(
      (response) => {
        if (response && response.updateList) {
          this.updateModelList();
        }
      }
    )
  }

  openAddEditCaliberDialog(prefs: { edit: boolean }): void {
    let dialogRef;
    if (prefs.edit) {
      dialogRef = this.dialog.open(DictionaryFormDialogComponent, { data: { service: this.caliberService, editId: this.caliberId } });
    } else {
      dialogRef = this.dialog.open(DictionaryFormDialogComponent, { data: { service: this.caliberService } });
    }
    dialogRef.afterClosed().subscribe(
      (response) => {
        if (response && response.updateList) {
          this.updateCaliberList();
        }
      }
    )
  }

  openAddEditTypeDialog(prefs: { edit: boolean }): void {
    let dialogRef;
    if (prefs.edit) {
      dialogRef = this.dialog.open(DictionaryFormDialogComponent, { data: { service: this.caliberService, editId: this.typeId } });
    } else {
      dialogRef = this.dialog.open(DictionaryFormDialogComponent, { data: { service: this.caliberService } });
    }
    dialogRef.afterClosed().subscribe(
      (response) => {
        if (response && response.updateList) {
          this.updateTypeList();
        }
      }
    )
  }

  openDeleteModelDialog(): void {
    const dialogRef = this.dialog.open(DeleteGenericDialogComponent, { data: { service: this.modelService, id: this.modelId } });
    dialogRef.afterClosed().subscribe(
      (response) => {
        if (response && response.updateList) {
          this.updateModelList();
        }
      }
    )
  }

  openDeleteCaliberDialog(): void {
    const dialogRef = this.dialog.open(DeleteGenericDialogComponent, { data: { service: this.caliberService, id: this.caliberId } });
    dialogRef.afterClosed().subscribe(
      (response) => {
        if (response && response.updateList) {
          this.updateCaliberList();
        }
      }
    )
  }

  openDeleteTypeDialog(): void {
    const dialogRef = this.dialog.open(DeleteGenericDialogComponent, { data: { service: this.typeService, id: this.typeId } });
    dialogRef.afterClosed().subscribe(
      (response) => {
        if (response && response.updateList) {
          this.updateTypeList();
        }
      }
    )
  }

  doAddOrEdit(): void {
    if (this.modelId && this.caliberId && this.typeId && this.productionYear) {
      if (this.editId) {
        console.log(this.editId);
        this.gunService.update({ id: this.editId, model: { id: this.modelId }, caliber: { id: this.caliberId }, type: { id: this.typeId }, productionYear: this.productionYear } as Gun).subscribe(
          () => {
            this.dialogRef.close({ updateList: true })
          }
        )
      } else {
        this.gunService.add({ modelId: this.modelId, caliberId: this.caliberId, typeId: this.typeId, productionYear: this.productionYear }).subscribe(
          () => {
            this.dialogRef.close({ updateList: true });
          }
        )
      }
    }
  }

}
