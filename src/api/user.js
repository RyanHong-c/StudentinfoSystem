import request from '@/utils/request.js'


export const registerService = (registerData) => {
    var params = new URLSearchParams()
    for (let key in registerData) {
        params.append(key, registerData[key])
    }
    return request.post('/user/register', params)
}

export const loginService = (loginData)=>{
    var params = new URLSearchParams()
    for(let key in loginData){
        params.append(key,loginData[key])
    }
    return request.post('/user/login',params)
}

export const userInfoService = ()=>{
    return request.get('/user/userInfo');
}

//修改个人信息
export const userInfoUpdateService = (userInfo)=>{
    return request.put('/user/update',userInfo)
}

export const userInforePasswordService = (userInfo)=>{
    return request.patch('/user/updatePassword',userInfo)
}