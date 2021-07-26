import { Component, EventEmitter, Input, OnInit, Output, SimpleChange, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Gun } from 'src/app/shared/types/Gun';
import { GunService } from 'src/app/services/rest/gun.service';

@Component({
  selector: 'app-gunlist',
  templateUrl: './gunlist.component.html',
  styleUrls: ['./gunlist.component.scss']
})
export class GunlistComponent implements OnInit {
  readonly displayedColumns: string[] = ["id", "model", "caliber", "production-year", "type"];

  private _dataSource: MatTableDataSource<Gun> = new MatTableDataSource;
  public get dataSource(): MatTableDataSource<Gun> {
    return this._dataSource;
  }
  public set dataSource(value: MatTableDataSource<Gun>) {
    this._dataSource = value;
  }

  private _selectedItem?: Gun | undefined = undefined;
  public get selectedItem(): Gun | undefined {
    return this._selectedItem;
  }
  public set selectedItem(value: Gun | undefined) {
    this._selectedItem = value;
  }

  @Output() selectionChanged = new EventEmitter<Gun>();
  @Input() viewUpdater: boolean = false;
  @ViewChild(MatSort, {static: false}) private _sort: MatSort = new MatSort;

  constructor(private gunService: GunService) { }

  ngOnInit(): void {
    this.updateList();
  }

  ngOnChanges(changes: { [property: string]: SimpleChange }) {
    let updateView: SimpleChange = changes['viewUpdater'];
    if (updateView) {
      this.updateList();
    }
  }

  updateList(): void {
    this.gunService.getAll().subscribe(
      (response: Gun[]) => {
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
        this.dataSource.sort = this._sort;
      }
    )
  }

  setSelectedItem(item: Gun): void {
    this.selectionChanged.emit(item);
    this.selectedItem = item;
  }
}
