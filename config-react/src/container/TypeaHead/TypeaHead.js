import React, { useEffect, useMemo, useState } from 'react'
import { SearchBar } from '../../components/SearchBar/SearchBar'
import {Card} from '../../components/Card/Card';
const trieInitialisation = require('../../Data Structure/trie');
// let itemsArray=["ant","bun","buffalo","animal","aeroplane","bangaram","zebra","zen","animation","cat","car","king","kite","knowledge","balu","attarintiki daredhi","abcd","elephant","eagle"];
export const TypeaHead=()=>{
    const [trie,setTrie] = useState();
    useMemo(()=>{
        setTrie(trieInitialisation());
    },[])
    const [cardItems,setCardItems] = useState();
    const [fieldState,setFieldState] = useState('');
    
    useEffect(()=>{
        if(fieldState.length)
        {
            setCardItems(trie.searchByString(fieldState));
        }
    },[fieldState,trie])


    const fieldChanging=(e)=>{
        setFieldState(e.target.value);
    }

    const setFieldStateByCardItem=(e,item)=>{     
        setFieldState(item.title);
        setCardItems(null);
    }


    return (<div style={{width:'600px',margin:'50px',position:'relative'}}>
        <SearchBar fieldChanging={(e)=>{fieldChanging(e)}} fieldState={fieldState}/>
        {fieldState.length?<Card styling={{position:'absolute',top:'44px',right:'0' ,zIndex:'10',width:'100%'}} setFieldStateByCardItem={(e,item)=>{setFieldStateByCardItem(e,item)}} items={cardItems}/>:null}
        </div>)
}