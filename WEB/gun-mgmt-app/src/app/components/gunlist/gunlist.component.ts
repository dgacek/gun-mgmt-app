import { Component, EventEmitter, OnInit, Output } from '@angular/core';
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

  constructor(private gunService :GunService) { }

  ngOnInit(): void {
    this.gunService.getAllGuns().subscribe(
      (response :Gun[]) => {
        this.dataSource = new MatTableDataSource(response);
      }
    )
  }

  setSelectedItem(item: Gun) {
    this.selectionChanged.emit(item);
    this.selectedItem = item;
  }

}
