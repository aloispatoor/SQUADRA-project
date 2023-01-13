import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Environment } from 'src/environment/environment';
import { Channel } from '../models/channel';
import { Message } from '../models/message';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private urlApi: string;
  public collection$: BehaviorSubject<Message[]>;
  public channel!: Channel;

  constructor(private httpClient: HttpClient) {
    this.urlApi = Environment.urlApi;
    this.collection$ = new BehaviorSubject<Message[]>([]);
  }

  public refresh(){
    this.httpClient.get<Message[]>(`${this.urlApi}/messages`).subscribe((data) =>{
      this.collection$.next(data);
    })
  }

  public add(id: number, message: Message): Observable<Message>{
    return this.httpClient
      .post<Message>(`${this.urlApi}/channels/${id}/messages`, message);
  }

  public getByChannel(id: number): Observable<Message[]>{
    return this.httpClient
      .get<Message[]>(`${this.urlApi}/channels/${id}/messages`);
  }

  public delete(id: number): Observable<Message>{
    return this.httpClient
      .delete<Message>(`${this.urlApi}/messages/delete/${id}`);
  }
}
