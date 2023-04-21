import { Component } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent {
  items = [
    {
      species: 'Catfish',
      weight: '5kg',
      length: '122cm',
      date: '2020-01-01',
      location: 'Poland/Wroclaw',
    },
    {
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
      email: 'john@example.com'
    },
    {
      name: 'Jane Doe',
      age: 27,
      email: 'jane@example.com'
    },
    {
      name: 'Bob Johnson',
      age: 45,
      email: 'bob@example.com'
    },
    {
      name: 'John Smith',
      age: 32,
      email: 'john@example.com'
    },
    {
      name: 'Jane Doe',
      age: 27,
      email: 'jane@example.com'
    },
    {
      name: 'Bob Johnson',
      age: 45,
      email: 'bob@example.com'
    },
    {
      name: 'John Smith',
      age: 32,
      email: 'john@example.com'
    },
    {
      name: 'Jane Doe',
      age: 27,
      email: 'jane@example.com'
    },
    {
      name: 'Bob Johnson',
      age: 45,
      email: 'bob@example.com'
    }
  ];
  
}
