import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DictionaryData } from 'src/app/models/DictionaryData';
import { Gun } from 'src/app/models/Gun';
import { Model } from 'src/app/models/Model';
import { CaliberService } from 'src/app/services/caliber.service';
import { GunService } from 'src/app/services/gun.service';
import { ModelService } from 'src/app/services/model.service';
import { TypeService } from 'src/app/services/type.service';
import { AddEditDictionaryDialogComponent } from '../add-edit-dictionary-dialog/add-edit-dictionary-dialog.component';
import { AddEditModelDialogComponent } from '../add-edit-model-dialog/add-edit-model-dialog.component';
import { DeleteGenericDialogComponent } from '../delete-generic-dialog/delete-generic-dialog.component';

@Component({
  selector: 'app-add-edit-gun-dialog',
  templateUrl: './add-edit-gun-dialog.component.html',
  styleUrls: ['./add-edit-gun-dialog.component.scss']
})
export class AddEditGunDialogComponent implements OnInit {
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
    public dialogRef: MatDialogRef<AddEditGunDialogComponent>,
    public dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public editId?: number
  ) { }

  ngOnInit(): void {
    this.updateModelList();
    this.updateCaliberList();
    this.updateTypeList();
    if (this.editId) {
      this.gunService.getGunById(this.editId).subscribe(
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
    this.modelService.getAllModels().subscribe(
      (response: Model[]) => {
        this.models = response;
      }
    )
  }

  updateCaliberList(): void {
    this.caliberService.getAllCalibers().subscribe(
      (response: DictionaryData[]) => {
        this.calibers = response;
      }
    )
  }

  updateTypeList(): void {
    this.typeService.getAllTypes().subscribe(
      (response: DictionaryData[]) => {
        this.types = response;
      }
    )
  }

  openAddEditModelDialog(prefs: { edit: boolean }): void {
    let dialogRef;
    if (prefs.edit) {
      dialogRef = this.dialog.open(AddEditModelDialogComponent, { data: this.modelId });
    } else {
      dialogRef = this.dialog.open(AddEditModelDialogComponent);
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
      dialogRef = this.dialog.open(AddEditDictionaryDialogComponent, { data: { mode: "caliber", editId: this.editId } });
    } else {
      dialogRef = this.dialog.open(AddEditDictionaryDialogComponent, { data: { mode: "caliber" } });
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
      dialogRef = this.dialog.open(AddEditDictionaryDialogComponent, { data: { mode: "type", editId: this.editId } });
    } else {
      dialogRef = this.dialog.open(AddEditDictionaryDialogComponent, { data: { mode: "type" } });
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
    const dialogRef = this.dialog.open(DeleteGenericDialogComponent, { data: { serviceMethodCallback: this.modelService.deleteModel.bind(this.modelService), id: this.modelId } });
    dialogRef.afterClosed().subscribe(
      (response) => {
        if (response && response.updateList) {
          this.updateModelList();
        }
      }
    )
  }

  openDeleteCaliberDialog(): void {
    const dialogRef = this.dialog.open(DeleteGenericDialogComponent, { data: { serviceMethodCallback: this.caliberService.deleteCaliber.bind(this.caliberService), id: this.caliberId } });
    dialogRef.afterClosed().subscribe(
      (response) => {
        if (response && response.updateList) {
          this.updateCaliberList();
        }
      }
    )
  }

  openDeleteTypeDialog(): void {
    const dialogRef = this.dialog.open(DeleteGenericDialogComponent, { data: { serviceMethodCallback: this.typeService.deleteType.bind(this.typeService), id: this.typeId } });
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
        this.gunService.updateGun({ id: this.editId, model: { id: this.modelId }, caliber: { id: this.caliberId }, type: { id: this.typeId }, productionYear: this.productionYear } as Gun).subscribe(
          () => {
            this.dialogRef.close({ updateList: true })
          }
        )
      } else {
        this.gunService.addGun({ modelId: this.modelId, caliberId: this.caliberId, typeId: this.typeId, productionYear: this.productionYear }).subscribe(
          () => {
            this.dialogRef.close({ updateList: true });
          }
        )
      }
    }
  }

}
