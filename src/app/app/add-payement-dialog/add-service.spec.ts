import { HttpClient } from '@angular/common/http';
import { AddService } from './add-service';

describe('AddService', () => {
  it('should create an instance', () => {
    const httpMock = jasmine.createSpyObj('httpClient',['post','get','delete']);
    const service = new AddService(httpMock as HttpClient);
    expect(service).toBeTruthy();
  });
});
