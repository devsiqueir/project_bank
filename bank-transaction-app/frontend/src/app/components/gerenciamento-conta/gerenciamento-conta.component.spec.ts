import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GerenciamentoContaComponent } from './gerenciamento-conta.component';

describe('GerenciamentoContaComponent', () => {
  let component: GerenciamentoContaComponent;
  let fixture: ComponentFixture<GerenciamentoContaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GerenciamentoContaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GerenciamentoContaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
