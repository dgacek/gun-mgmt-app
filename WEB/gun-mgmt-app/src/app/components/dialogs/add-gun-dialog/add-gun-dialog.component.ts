import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { DictionaryData } from 'src/app/models/DictionaryData';
import { GunInput } from 'src/app/models/GunInput';
import { Model } from 'src/app/models/Model';
import { CaliberService } from 'src/app/services/caliber.service';
import { GunService } from 'src/app/services/gun.service';
import { ModelService } from 'src/app/services/model.service';
import { TypeService } from 'src/app/services/type.service';

@Component({
  selector: 'app-add-gun-dialog',
  templateUrl: './add-gun-dialog.component.html',
  styleUrls: ['./add-gun-dialog.component.scss']
})
export class AddGunDialogComponent implements OnInit {
  models? :Model[] = undefined;
  calibers? :DictionaryData[] = undefined;
  types? :DictionaryData[] = undefined;
  modelId? :number = undefined;
  caliberId? :number = undefined;
  typeId? :number = undefined;
  productionYear? :number = undefined;

  constructor(private modelService :ModelService, 
    private caliberService :CaliberService,
    private typeService :TypeService, 
    private gunService :GunService,
    public dialogRef :MatDialogRef<AddGunDialogComponent>
    ) { }

  ngOnInit(): void {
    this.modelService.getAllModels().subscribe(
      (response :Model[]) => {
        this.models = response;
      }
    )
    this.caliberService.getAllCalibers().subscribe(
      (response :DictionaryData[]) => {
        this.calibers = response;
      }
    )
    this.typeService.getAllTypes().subscribe(
      (response :DictionaryData[]) => {
        this.types = response;
      }
    )
  }

  doAdd(): void {
    if (this.modelId && this.caliberId && this.typeId && this.productionYear) {
      this.gunService.addGun({modelId: this.modelId, caliberId: this.caliberId, typeId: this.typeId, productionYear: this.productionYear}).subscribe(
        () => {
          this.dialogRef.close({updateList: true});
        }
      )
    }
  }

}
