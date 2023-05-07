import { Component, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-entry',
  templateUrl: './entry.component.html',
  styleUrls: ['./entry.component.scss'],
})
export class EntryComponent {
  end_point = 'http://localhost:8080/api/entry/';

  constructor(private http: HttpClient) {}

  fish = {
    id: this.generateRandomId(20),
    weight: '0',
    length: '0',
    species: '',
    date: '',
    location: '',
    userId: '1',
  };

  generateRandomId(length: number): string {
    let result = '';
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';

    for (let i = 0; i < length; i++) {
      result += characters.charAt(Math.floor(Math.random() * characters.length));
    }

    return result;
  }

  onSubmit() {
    this.fish.length = this.fish.length.toString();
    this.fish.weight = this.fish.weight.toString();

    this.http.post(this.end_point, this.fish).subscribe(
      (response: any) => {
        console.log(response);
      },
      (error: any) => {
        console.error(error);
      }
    );
  }
}
