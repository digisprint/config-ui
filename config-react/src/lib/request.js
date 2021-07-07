
import axios from 'axios'
import swal from 'sweetalert';

const getUrl = () => {
    let isDev = true
    let baseURL = 'http://35.224.205.52:8080/'
    if (isDev) {
        baseURL = 'http://localhost:8010/proxy/'
    }
    return baseURL
}
const getHeaders = () => {
    return {
        'token': 'eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJDb25nZmlnU2VydmVyIiwic3ViIjoiQ29uZmlnTG9naW4iLCJ1c2VyTmFtZSI6ImFkbWluIiwidXNlcklkIjoiNjBkZWU1NTQxNDQ0NDU1MDQzYTIzN2Q5Iiwicm9sZXMiOlt7ImlkIjoiYWRtaW4iLCJyb2xlTmFtZSI6IlN1cGVyIGFkbWluIn1dLCJpYXQiOjE2MjUyMjIyNTEsImV4cCI6MTYyNTIyMjI1MX0.bL1qVWBLgTi7SGe5xLv7kk4ELNDtX96KTPtasJxRT8JY0N5SODhOeIzysaoJCE5kDYyaU1jKn_LZWZfXGFrEAw'
    }
}
export const request = async (url, headers = {}) => {
    try {
        const { data } = await axios.get(getUrl() + url, {
            headers: getHeaders()
        })
        if (!data || !data.body) {
            throw Error('Empty data from get settings')
        }
        return data
    } catch (error) {
        swal(error.message)
    }
}