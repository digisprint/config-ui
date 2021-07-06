const imageUrl = require('../assests/ImageUrl');
class Node{
    constructor(title,items){
        this.title = title;
        this.items=items;
        this.children = new Array(26);
    }
}
///////////////////////////////////////
class Trie{
    constructor(items){
        this.root = new Node('\0',items);
    }
//////////////////////////////////////
    add(word){
        let curr = this.root;
        for(let i=0;i<word.length;i++){
            let substr = word.substring(0,i+1);
            let character = word[i];
            let index = character.charCodeAt(0)-97;
            if(!curr.children[index]){
                const newNode = new Node(character,this.searchForValues(substr,curr.items));
                curr.children[index] = newNode;
                curr = curr.children[index];
            }
            else{
                curr = curr.children[index];
            }
        }
    }
/////////////////////////////////////////

    searchForValues(substring,itemsList){
        return itemsList.filter(item=>{
            return item.title.includes(substring);
        })
    }

    ///////////////////////////////////////////////////

    searchByString(searchString){
        let curr = this.root;
        for(var i=0;i<searchString.length;i++){
            let index = searchString.charCodeAt(i)-97;
            curr = curr.children[index];
            if(!curr){
                return null;
            }
        }
        return curr.items;
    }

}


let initialisation=()=>{
    // let array = ["ant","buffalo","biryani","beach","bangaram","ask","animal","yalk","zebra","judge","apple"];
    let newArray=[{title:'ant',imageUrl:imageUrl.ant},
    {title:'buffalo',imageUrl:imageUrl.buffalo},
    {title:'biryani',imageUrl:imageUrl.biryani},
    {title:'beach',imageUrl:imageUrl.beach},
    {title:'bangaram',imageUrl:imageUrl.bangaram},
    {title:'ask',imageUrl:imageUrl.ask},
    {title:'animal',imageUrl:imageUrl.animal},
    {title:'yalk',imageUrl:imageUrl.yalk},
    {title:'zebra',imageUrl:imageUrl.zebra},
    {title:'judge',imageUrl:imageUrl.judge},
    {title:'apple',imageUrl:imageUrl.apple}]
    let trie = new Trie(newArray);
    newArray.forEach(item=>{
        trie.add(item.title);
    })
    return trie;   
}


module.exports = initialisation;