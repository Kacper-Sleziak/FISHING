import { Component } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent {

  showModal = false;

  deleteItem(index: number) {
    this.fishes.splice(index, 1);
  }
  openModal(item: any) {
    console.log(item)
    this.selectedFish = item;
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
  }

  updateFish() {
    console.log(this.selectedFish)
  }

  selectedFish: any = {
    id: 0,
    species: '',
    weight: 0,
    length: 0,
    date: '',
    location: ''
  };

  fishes = [
    {
      id: 0,
      species: 'Catfish',
      weight: '5kg',
      length: '122cm',
      date: '2020-01-01',
      location: 'Poland/Wroclaw',
    },
    {
      id: 1,
      species: 'Bass',
      weight: '2kg',
      length: '60cm',
      date: '2020-02-01',
      location: 'USA/California',
    },
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
