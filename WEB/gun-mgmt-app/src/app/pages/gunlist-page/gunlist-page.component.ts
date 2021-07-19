import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Gun } from 'src/app/models/Gun';
import { GunService } from 'src/app/services/gun.service';

@Component({
  selector: 'app-gunlist-page',
  templateUrl: './gunlist-page.component.html',
  styleUrls: ['./gunlist-page.component.scss']
})
export class GunlistPageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
}
