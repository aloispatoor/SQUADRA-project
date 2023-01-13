import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { Message } from 'src/app/core/models/message';
import { MessageService } from 'src/app/core/services/message.service';

@Component({
  selector: 'app-page-list-messages',
  templateUrl: './page-list-messages.component.html',
  styleUrls: ['./page-list-messages.component.scss']
})
export class PageListMessagesComponent {
  public messages$!: BehaviorSubject<Message[]>;
  public filteredMessages!: Observable<Message[]>;
  public newMessage: Message;
  public form: FormGroup;
  public channelId!: number;


  constructor(
    private formBuilder: FormBuilder,
    private service: MessageService,
    private router: Router,
    private activatedRoute: ActivatedRoute){

    this.messages$ = this.service.collection$;

    this.newMessage = new Message();
    this.form = this.formBuilder.group({
      channelId: [this.newMessage.channelId],
      id: [this.newMessage.id],
      content: [this.newMessage.content],
    })

    this.activatedRoute.params.subscribe((params) => {
      this.channelId = params['id'];
      this.filteredMessages = this.service.getByChannel(this.channelId);
      console.log(this.filteredMessages);
    })

    this.service.refresh();
  }


  public onSubmit(){
    if(this.newMessage.content != ""){
      this.service.add(this.channelId, this.form.value).subscribe(()=> {
        this.router.navigate([`/channels/${this.channelId}`]);
        console.log("Message sent");
      });
    }
  }


  public deleteMessage(id: number){
    this.service.delete(id).subscribe();
    console.log("Message removed!");
  }
}
