import request from '@/utils/request.js'
import { useTokenStore } from '@/stores/token.js';

export const studentCategoryListService = ()=>{

    // const tokenStore = useTokenStore()
    // tokenStore.token
    // return request.get('/category', { headers: { 'Authorization': tokenStore.token } })
    return request.get('/category/list')
}

export const studentCategoryAddService = (categoryModel) => {
    return request.post('/category/add', categoryModel)
}

//修改分类
export const studentCategoryUpdateService = (categoryModel)=>{
    return request.put('/category/update',categoryModel)
}

export const studentCategoryDeleteService = (id) => {
    return request.delete('/category/delete?id='+id)
}

export const studentListService = (params) => {
    return request.get('/student/list', { params: params })
}

export const studentAddService = (studentModel)=>{
    return request.post('/student/add',studentModel)
}

export const studentUpdateService = (studentModel)=>{
    return request.put('/student/update',studentModel)
}

export const studentDeleteService = (id) => {
    return request.delete('/student/delete?id='+id)
}