import { Component, OnInit } from '@angular/core';
import { Gun } from 'src/app/models/Gun';

@Component({
  selector: 'app-gunlist-page',
  templateUrl: './gunlist-page.component.html',
  styleUrls: ['./gunlist-page.component.scss']
})
export class GunlistPageComponent implements OnInit {
  selectedItem? :Gun = undefined; 

  constructor() { }

  ngOnInit(): void {
  }

  setSelectedItem(item :Gun) {
    this.selectedItem = item;
    console.log(item);
  }
}
