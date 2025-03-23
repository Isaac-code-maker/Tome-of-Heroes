import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CharacterCreateComponent } from './character-create.component';

describe('CharacterCreateComponent', () => {
  let component: CharacterCreateComponent;
  let fixture: ComponentFixture<CharacterCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CharacterCreateComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CharacterCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
