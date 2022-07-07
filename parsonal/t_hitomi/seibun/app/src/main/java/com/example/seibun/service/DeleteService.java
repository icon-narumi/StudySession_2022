package com.example.seibun.service;
// package com.example.okaimono.service;

// import java.math.BigDecimal;
// import java.util.List;

// // import com.example.okaimono.entity.EditEntity;
// import com.example.okaimono.entity.SearchEntity;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class DeleteService {

//     @Autowired
//     private DataDeleteMapper dataDeleteMapper;

//     public void editEntity(String category,String name) {

//         dataDeleteMapper.deleteEntity(category,name);
//     }



// }



// @Service
// public class DeleteService {
    
//     @Autowired
//     SearchService searchService;
//     public List<SearchEntity> getResultList(String search,String sortList,String sort) {

//         if( search.isEmpty() ) {
//             return searchService.orderList(sortList,sort);
//         } else {
//             return searchService.orderSearchList(search,sortList,sort);
//         }
//     }

//     DeleteService deleteService;
//     public List<DeleteEntity> getDeleteList(String category,String name) {
//         return deleteService.getList(category,name);
//     }


//     AddService addService;
//     public void editEntity(String addCategory,String addName,String addColor,BigDecimal addCalorie,BigDecimal addProtein) {
//         addService.editEntity(addCategory,addName,addColor,addCalorie,addProtein);
//     }

// }
