import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Gun } from 'src/app/models/Gun';
import { GunService } from 'src/app/services/gun.service';

@Component({
  selector: 'app-gunlist',
  templateUrl: './gunlist.component.html',
  styleUrls: ['./gunlist.component.scss']
})
export class GunlistComponent implements OnInit {
  displayedColumns :string[] = ["id", "model", "caliber", "production-year"];
  dataSource? :MatTableDataSource<Gun> = undefined;
  @Output() selectionChanged = new EventEmitter<Gun>();
  selectedItem? :Gun = undefined;
  
  @ViewChild(MatSort, { static: false }) set sort(val: MatSort) {
    if (val && this.dataSource) {
      this.dataSource.sort = val;
    }
  }

  constructor(private gunService :GunService) { }

  ngOnInit(): void {
    this.gunService.getAllGuns().subscribe(
      (response :Gun[]) => {
        this.dataSource = new MatTableDataSource(response);
        this.dataSource.sortingDataAccessor = (item, property) => {
          switch (property) {
            case 'id': return item.id;
            case 'model': return `${item.model.manufacturer.name} ${item.model.name}`;
            case 'caliber': return item.caliber.name;
            case 'production-year': return item.productionYear;
            case 'type': return item.type.name;
            default: return "";
          }
        };
      }
    )
  }

  setSelectedItem(item: Gun) {
    this.selectionChanged.emit(item);
    this.selectedItem = item;
  }

}
