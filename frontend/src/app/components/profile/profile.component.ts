import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent {
  constructor(private http: HttpClient) {}
  endPoint = 'http://localhost:8080/api/entry/user/1';
  showModal = false;

  ngOnInit(): void {
    this.http.get(this.endPoint).subscribe((data: any) => {
      this.fishes = data
    });
  }
  
  deleteItem(index: number) {
    this.fishes.splice(index, 1);
  }
  openModal(item: any) {
    console.log(item);
    this.selectedFish = item;
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
  }

  updateFish() {
    console.log(this.selectedFish);
  }

  selectedFish: any = {
    id: 0,
    species: '',
    weight: 0,
    length: 0,
    date: '',
    location: '',
  };

  fishes = [
    {
    id: 0,
    species: '',
    weight: 0,
    length: 0,
    date: '',
    location: '',
    }
  ];

  friends = [
    {
      name: 'John Smith',
      age: 32,
      email: 'john@example.com',
    },
    {
      name: 'Jane Doe',
      age: 27,
      email: 'jane@example.com',
    },
    {
      name: 'Bob Johnson',
      age: 45,
      email: 'bob@example.com',
    },
    {
      name: 'John Smith',
      age: 32,
      email: 'john@example.com',
    },
    {
      name: 'Jane Doe',
      age: 27,
      email: 'jane@example.com',
    },
    {
      name: 'Bob Johnson',
      age: 45,
      email: 'bob@example.com',
    },
    {
      name: 'John Smith',
      age: 32,
      email: 'john@example.com',
    },
    {
      name: 'Jane Doe',
      age: 27,
      email: 'jane@example.com',
    },
    {
      name: 'Bob Johnson',
      age: 45,
      email: 'bob@example.com',
    },
  ];
}
