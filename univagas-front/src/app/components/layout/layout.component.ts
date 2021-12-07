import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
  openedSidenav: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  openSidenav() {
    this.openedSidenav = !this.openedSidenav;
  }
}
