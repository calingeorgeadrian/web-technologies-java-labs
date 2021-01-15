import { Injectable } from '@angular/core';
import { GameModel } from '../models/game.model';

@Injectable({ providedIn: 'root' })
export class BGGService {
  req = new XMLHttpRequest();

  constructor() { }

  getUserCollection() {
    this.req.open("GET", "https://www.boardgamegeek.com/xmlapi2/collection?username=RoyalFlush37&subtype=boardgame&own=1&stats=1", false);
    this.req.send(null);
    var parser, xmlDoc;
    var games: GameModel[] = [];

    parser = new DOMParser();
    xmlDoc = parser.parseFromString(this.req.responseText, "text/xml");

    var items = xmlDoc.getElementsByTagName("item");

    for (let i = 0; i < items.length; i++) {
      var id = items[i].getAttribute('objectid');
      var type = items[i].getAttribute('subtype');
      var title = items[i].getElementsByTagName('name')[0].innerHTML;
      var year = (items[i].getElementsByTagName("yearpublished")[0] != undefined ? items[i].getElementsByTagName("yearpublished")[0].innerHTML : 2040);
      var imageUrl = (items[i].getElementsByTagName("image")[0] != undefined ? items[i].getElementsByTagName("image")[0].childNodes[0].nodeValue : null);
      var minPlayers = (items[i].getElementsByTagName("stats")[0].getAttribute('minplayers'));
      var maxPlayers = (items[i].getElementsByTagName("stats")[0].getAttribute('maxplayers'));
      var playingTime = (items[i].getElementsByTagName("stats")[0].getAttribute('minplaytime') != undefined ? items[i].getElementsByTagName("stats")[0].getAttribute('minplaytime') : 0);
      games.push({
        id: null,
        bggId: id,
        title: title,
        description: null,
        type: type,
        imageUrl: imageUrl,
        year: year,
        minPlayers: minPlayers,
        maxPlayers: maxPlayers,
        playingTime: playingTime,
        price: 1,
        newPrice: 1,
        stock: 0,
        dateAdded: null,
        dateModified: null
      });
    }

    return games;
  }

  getGame(id: any) {
    this.req.open("GET", "https://www.boardgamegeek.com/xmlapi2/thing?id=" + id + "&stats=1", false);
    this.req.send(null);
    var parser, xmlDoc;
    var game = new GameModel();

    parser = new DOMParser();
    xmlDoc = parser.parseFromString(this.req.responseText, "text/xml");

    game.bggId = xmlDoc.getElementsByTagName("item")[0].getAttribute('id');
    game.title = xmlDoc.getElementsByTagName("name")[0].getAttribute('value');
    game.type = xmlDoc.getElementsByTagName("item")[0].getAttribute('type');
    game.imageUrl = xmlDoc.getElementsByTagName("image")[0].childNodes[0].nodeValue;
    game.description = xmlDoc.getElementsByTagName("description")[0].innerHTML;
    game.year = xmlDoc.getElementsByTagName("yearpublished")[0].getAttribute('value');
    game.minPlayers = xmlDoc.getElementsByTagName("minplayers")[0].getAttribute('value');
    game.maxPlayers = xmlDoc.getElementsByTagName("maxplayers")[0].getAttribute('value');
    game.playingTime = xmlDoc.getElementsByTagName("minplaytime")[0].getAttribute('value');

    return game;
  }

}
