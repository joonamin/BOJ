cmake_minimum_required(VERSION 3.17)
project(BOJ)
set(CMAKE_CXX_STANDARD 17)

#Boost 라이브러리 설치 확인
find_package(Boost)

if(Boost_FOUND)
    include_directories(${Boost_INCLUDE_DIRS})
endif()
add_executable(BOJ main.cpp)