<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'

import { ref } from 'vue'

//文章分类数据模型
const categorys = ref([

])

//用户搜索时选中的分类id
const categoryId = ref('')

//用户搜索时选中的发布状态
const state = ref('')

//文章列表数据模型
const students = ref([
])

//分页条数据模型
const pageNum = ref(1)//当前页
const total = ref(20)//总条数
const pageSize = ref(3)//每页条数

//当每页条数发生了变化，调用此函数
const onSizeChange = (size) => {
    pageSize.value = size
    getStudents()
}
//当前页码发生变化，调用此函数
const onCurrentChange = (num) => {
    pageNum.value = num
    getStudents()
}

import { studentCategoryListService, studentListService,studentAddService,studentUpdateService,studentDeleteService } from '@/api/student.js'
const studentCategoryList = async () => {
    let result = await studentCategoryListService();

    categorys.value = result.data;
}


const getStudents = async () => {
    let params = {
        pageNum: pageNum.value,
        pageSize: pageSize.value,
        categoryId: categoryId.value ? categoryId.value : null,
        state: state.value ? state.value : null
    }
    let result = await studentListService(params);
    //渲染列表数据
    students.value = result.data.items

    //渲染总条数
    for (let i = 0; i < students.value.length; i++) {
        let student = students.value[i];
        for (let j = 0; j < categorys.value.length; j++) {
            if (student.categoryId === categorys.value[j].id) {
                student.categoryName = categorys.value[j].categoryName
            }
        }
    }
    total.value = result.data.total
}
studentCategoryList();
getStudents()

import { Plus } from '@element-plus/icons-vue'
//控制抽屉是否显示
const visibleDrawer = ref(false)
//添加表单数据模型
const studentModel = ref({
    name: '',
    age:'',
    categoryId: '',
    address:'',
    state:''
})

import { ElMessage,ElMessageBox } from 'element-plus';
const addStudent=async (cstate)=>{
    studentModel.value.state = cstate
    let result = await studentAddService(studentModel.value);
    ElMessage.success(result.msg? result.msg:'添加成功')
    
    visibleDrawer.value=false

    getStudents()
}
const deleteStudent = (row) => {
    studentDeleteService(row.id);
    ElMessageBox.confirm(
        '你确认删除该学生信息吗？',
        '温馨提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(() => {
            //用户点击了确认
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
            getStudents()

        })
        .catch(() => {
            //用户点击了取消
            ElMessage({
                type: 'info',
                message: '取消删除',
            })
        })
}

</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>学生信息管理</span>
                <div class="extra">
                    <el-button type="primary" @click="visibleDrawer=true">添加学生信息</el-button>
                </div>
            </div>
        </template>
        <!-- 搜索表单 -->
        <el-form inline>
            <el-form-item label="班级分类：">
                <el-select placeholder="请选择" v-model="categoryId">
                    <el-option v-for="c in categorys" :key="c.id" :label="c.categoryName" :value="c.id">
                    </el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="上传状态：">
                <el-select placeholder="请选择" v-model="state">
                    <el-option label="已发布" value="已发布"></el-option>
                    <el-option label="草稿" value="草稿"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="getStudents">搜索</el-button>
                <el-button @click="categoryId = ''; state = ''">重置</el-button>
            </el-form-item>
        </el-form>
        <!-- 文章列表 -->
        <el-table :data="students" style="width: 100%">
            <el-table-column label="学生姓名" width="200" prop="name"></el-table-column>
            <el-table-column label="年龄" prop="age"></el-table-column>
            <el-table-column label="班级分类" prop="categoryName"></el-table-column>
            <el-table-column label="更新时间" prop="createTime"> </el-table-column>
            <el-table-column label="学号" prop="address"></el-table-column>
            <el-table-column label="状态" prop="state"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <!-- <el-button :icon="Edit" circle plain type="primary"></el-button> -->
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteStudent(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>
        <!-- 分页条 -->
        <el-pagination v-model:current-page="pageNum" v-model:page-size="pageSize" :page-sizes="[3, 5, 10, 15]"
            layout="jumper, total, sizes, prev, pager, next" background :total="total" @size-change="onSizeChange"
            @current-change="onCurrentChange" style="margin-top: 20px; justify-content: flex-end" />

        <!-- 抽屉 -->
        <el-drawer v-model="visibleDrawer" title="添加学生信息" direction="rtl" size="50%">
            <!-- 添加文章表单 -->
            <el-form :model="studentModel" label-width="100px">
                <el-form-item label="学生姓名">
                    <el-input v-model="studentModel.name" placeholder="请输入学生姓名"></el-input>
                </el-form-item>
                <el-form-item label="年龄">
                    <el-input v-model="studentModel.age" placeholder="请输入学生年龄"></el-input>
                </el-form-item>
                <el-form-item label="班级">
                    <el-select placeholder="请选择分类" v-model="studentModel.categoryId">
                        <el-option v-for="c in categorys" :key="c.id" :label="c.categoryName" :value="c.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="学号">
                    <el-input v-model="studentModel.address" placeholder="请输入学号"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="addStudent('已发布')">上传</el-button>
                    <el-button type="info" @click="addStudent('草稿')">草稿</el-button>
                </el-form-item>
            </el-form>
        </el-drawer>
    </el-card>
</template>
<style lang="scss" scoped>
.el-select {
    width: 220px;
}

.page-container {
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}
/* 抽屉样式 */
// .avatar-uploader {
//     :deep() {
//         .avatar {
//             width: 178px;
//             height: 178px;
//             display: block;
//         }

//         .el-upload {
//             border: 1px dashed var(--el-border-color);
//             border-radius: 6px;
//             cursor: pointer;
//             position: relative;
//             overflow: hidden;
//             transition: var(--el-transition-duration-fast);
//         }

//         .el-upload:hover {
//             border-color: var(--el-color-primary);
//         }

//         .el-icon.avatar-uploader-icon {
//             font-size: 28px;
//             color: #8c939d;
//             width: 178px;
//             height: 178px;
//             text-align: center;
//         }
//     }
// }
// .editor {
//   width: 100%;
//   :deep(.ql-editor) {
//     min-height: 200px;
//   }
// }
</style>