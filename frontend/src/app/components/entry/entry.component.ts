import { Component, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-entry',
  templateUrl: './entry.component.html',
  styleUrls: ['./entry.component.scss'],
})
export class EntryComponent {
  fish = {
    weight: 0,
    length: 0,
    species: '',
    date: '',
    location: '',
  };

  onSubmit() {
    console.log(this.fish);
  }
}
