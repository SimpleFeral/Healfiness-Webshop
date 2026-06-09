import { Component } from '@angular/core';
import {MatGridListModule} from '@angular/material/grid-list';


@Component({
  selector: 'app-shop-info',
  imports: [MatGridListModule],
  templateUrl: './shop-info.html',
  styleUrl: './shop-info.scss',
})
export class ShopInfo {}
