import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { UserService } from 'src/app/services/rest/user.service';
import { User } from 'src/app/shared/types/User';

@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.scss']
})
export class UserlistComponent implements OnInit {
  readonly displayedColumns: string[] = ["actions", "id", "username", "email", "phone", "role"];
  private _dataSource: MatTableDataSource<User> = new MatTableDataSource;
  public get dataSource(): MatTableDataSource<User> {
    return this._dataSource;
  }
  public set dataSource(value: MatTableDataSource<User>) {
    this._dataSource = value;
  }

  @ViewChild(MatSort, {static: false}) private _sort: MatSort = new MatSort;
  @Input() viewUpdater: boolean = false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  updateList(): void {
    this.userService.getAll().subscribe(
      (response: User[]) => {
        this.dataSource = new MatTableDataSource(response);
        this.dataSource.sortingDataAccessor = (item, property) => {
          switch (property) {
            case 'id': return item.id;
            case 'username': return item.username;
            case 'email': return item.email;
            case 'phone': return item.phone;
            case 'role': return item.role.name;
            default: return "";
          }
        };
        this.dataSource.sort = this._sort;
      }
    )
  }

}
