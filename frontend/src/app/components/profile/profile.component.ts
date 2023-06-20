import { Component, Renderer2 } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as jsonld from 'jsonld';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent {
  constructor(private http: HttpClient, private renderer: Renderer2) {}
  endPoint = 'http://localhost:8080/api/entry/user/1';
  showModal = false;

  ngOnInit(): void {
    this.http.get(this.endPoint).subscribe((data: any) => {
      this.fishes = data;
    });
    this.generateJsonLdScript();

    const person = {
      '@context': 'https://schema.org/',
      '@type': 'Person',
      name: '{{friend.anme}}',
      age: '{{friend.age}}',
      email: '{{friend.email}}',
    };

    jsonld.expand(person, (err, expanded) => {
      if (err) {
        console.error('Error expanding JSON-LD:', err);
        return;
      }
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

  generateJsonLdScript() {
    const script = this.renderer.createElement('script');
    script.setAttribute('type', 'application/ld+json');

    const jsonLdData = {
      '@context': 'https://schema.org/',
      '@type': 'Person',
      name: '{{friend.name}}',
      age: '{{friend.age}}',
      email: '{{friend.email}}',
    };
    script.innerHTML = JSON.stringify(jsonLdData);
    this.renderer.appendChild(document.head, script);
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
