import React, {useState,useEffect} from 'react'
import './components/Tabs/Tabs.css'
import StaticKeys from './templates/UI/StaticKeys'
import Tabs from './components/Tabs/Tabs'
import ConfigList from './templates/UI/ConfigList'
import {Link , Route} from 'react-router-dom'
import axios from 'axios'
import swal from 'sweetalert';

function NavBar(props) {
  const [ response,setResponse ] = useState()

            // useEffect(()=> {
                
            //     const PROXY_URL = 'https://cors-anywhere.herokuapp.com/';
            //     const URL = 'http://35.224.205.52:8080/config';
            //     const options = {
            //         headers: {'X-Requested-With': 'XMLHttpRequest'}
            //       };
            //       //axios.post('/save', { a: 10 }, options);
            //     axios.defaults.headers.post['Content-Type'] ='application/json;charset=utf-8';
            //     axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
            
            //     axios.get(PROXY_URL + URL, options)
            //     .then((response)=> {
            //         const result = response.data
            //         setResponse(result)
            //         //console.log('result',result)
                    
            //     })
                
            //     .catch((err)=>{
            //         swal(err.message)
            //     })
            // }, [])
    return(
        <div>
        <nav class="navbar navbar-expand-lg" style={{'marginTop': '94px', 'marginLeft': '5.5rem'}}>
  <div class="container-fluid">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item active">
          <Link class="nav-link" aria-current="page" href="#" to='/StaticKeys'>StaticKeys</Link>
        </li>
        <li class="nav-item">
          <Link class="nav-link" href="#" to='/ConfigList'>ConfigList</Link>
        </li>
        <li class="nav-item">
          <Link class="nav-link" href="#" to='/Tabs'>Map Confingurations</Link>
        </li>      
      </ul>
    </div>
  
  </div>
</nav>
<Route path ="/StaticKeys" component ={StaticKeys} />
    <Route path ="/configList" render ={(props)=>{
                return <ConfigList {...props}/>
            }}/>
    <Route path ="/Tabs" component ={Tabs} />
</div>
    )
}
export default NavBar