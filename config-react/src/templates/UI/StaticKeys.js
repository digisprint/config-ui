import React, {useState,useEffect} from 'react'
//import { TableDataStatic } from '../../container/TableData/TableDataStatic';
import DataListStatic from '../../templates/UI/DataListStatic'
import {ModalContainer} from '../../container/Modals/Modals'
import axios from 'axios'
import swal from 'sweetalert';

function StaticKeys(props) {
    const [modalState,setModalState] = useState(false);

    const handleOpen = () => {
        setModalState(true)
      };
    
      const handleClose = () => {
        setModalState(false)
      };

      const [ response,setResponse ] = useState()

            useEffect(()=> {            
                //setResponse(data)
                
                //const PROXY_URL = 'https://cors-anywhere.herokuapp.com/';
                const URL = 'http://35.224.205.52:8080/global-config/config/staticKeys';
                //const PROXY_URL = 'http://localhost:8010/proxy/global-config/config/staticKeys'
                const options = {
                    headers: {'token': 'eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJDb25nZmlnU2VydmVyIiwic3ViIjoiQ29uZmlnTG9naW4iLCJ1c2VyTmFtZSI6ImFkbWluIiwidXNlcklkIjoiNjBkZWU1NTQxNDQ0NDU1MDQzYTIzN2Q5Iiwicm9sZXMiOlt7ImlkIjoiYWRtaW4iLCJyb2xlTmFtZSI6IlN1cGVyIGFkbWluIn1dLCJpYXQiOjE2MjUyMjIyNTEsImV4cCI6MTYyNTIyMjI1MX0.bL1qVWBLgTi7SGe5xLv7kk4ELNDtX96KTPtasJxRT8JY0N5SODhOeIzysaoJCE5kDYyaU1jKn_LZWZfXGFrEAw'}
                  };
                  //axios.post('/save', { a: 10 }, options);
                // axios.defaults.headers.post['Content-Type'] ='application/json;charset=utf-8';
                // axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
                
                axios.get(URL, options)
                .then((response)=> {
                    const result = response.data
                    setResponse(result)
                    console.log('result',result)
                    
                })
                
                .catch((err)=>{
                    swal(err.message)
                })
            }, [])
            // console.log('resulssstsss',data)
            //console.log('resulssst',response)

    return (<React.Fragment>
      {(response!==undefined)?<DataListStatic handleOpen={handleOpen} list={response}/>:null}        
        <ModalContainer modalState = {modalState} handleClose = {handleClose}/>
    </React.Fragment>)
}
export default StaticKeys