import React, {useState,useEffect} from 'react'
//import {Tabledata} from '../../container/TableData'
import { Grid } from '@material-ui/core';
import DataList from './DataList';
import  './dataList.css'
import axios from 'axios'
import swal from 'sweetalert';


//const OtherComponent = React.lazy(() => import('./DataList'));

// const data = 
// {
//     "message": "Displaying all configurations of configList",
//     "success": true,
//     "statusCode": 200,
//     "body": [
//         {
//             "key": "site.data",
//             "value": [
//                 "Liverpool",
//                 "Suburbia",
//                 "William Sonama"
//             ]
//         },
//         {
//             "key": "environments",
//             "value": [
//                 "Local",
//                 "SIT",
//                 "QA",
//                 "PROD"
//             ]
//         }
//     ]
// }



        function ConfigList(props) {
            const [ response,setResponse ] = useState()
            const { history } = props

            useEffect(()=> {

                //setResponse(data)
                
                const PROXY_URL = 'https://cors-anywhere.herokuapp.com/';
                const URL = 'http://35.224.205.52:8080/config/configList';
                const options = {
                    headers: {'X-Requested-With': 'XMLHttpRequest'}
                  };
                  //axios.post('/save', { a: 10 }, options);
                axios.defaults.headers.post['Content-Type'] ='application/json;charset=utf-8';
                axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
            
                axios.get(PROXY_URL + URL, options)
                .then((response)=> {
                    const result = response.data
                    setResponse(result)
                    //console.log('result',result)
                    
                })
                
                .catch((err)=>{
                    swal(err.message)
                })
            }, [])
            // console.log('resulssstsss',data)
            //console.log('resulssst',response)

    return (
    <React.Fragment>
      <Grid className="container">
        <Grid>
        {/* <TypeaHead /> */}
        <div className="bdr-top p-3">
            {(response!==undefined)?<DataList list={response} />:null}
        </div>
        </Grid>
        </Grid>
    </React.Fragment>)
}
export default ConfigList