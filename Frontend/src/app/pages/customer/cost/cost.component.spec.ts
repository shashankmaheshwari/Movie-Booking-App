import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CostComponent } from './cost.component';
import { LoginService } from 'src/app/services/login.service';
import { MovieService } from 'src/app/services/movie.service';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('CostComponent', () => {
  let component: CostComponent;
  let fixture: ComponentFixture<CostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[HttpClientTestingModule,
        RouterTestingModule],
      declarations: [ CostComponent ],
      providers:[ MovieService,LoginService ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
