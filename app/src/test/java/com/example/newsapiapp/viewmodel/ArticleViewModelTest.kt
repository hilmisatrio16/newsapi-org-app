package com.example.newsapiapp.viewmodel

import com.example.newsapiapp.model.article.ResponseDataArticle
import com.example.newsapiapp.model.article.ResponseDataSource
import com.example.newsapiapp.network.ApiService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class ArticleViewModelTest{
    lateinit var service : ApiService

    @Before
    fun setUp(){
        service = mockk()
    }
    @Test
    fun testRetriveDataArticle() : Unit = runBlocking {
        val responseRetrive = mockk<Call<ResponseDataArticle>>()

        //membuat objek palsu (mock) responseRetrive dari kelas Call<List<Source>>
        //Objek palsu ini akan digunakan sebagai respons palsu dari pemanggilan service.getAllSources().

        every {
            runBlocking {
                service.getAllArticle("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyaWQiOiI1IiwiZXhwIjoxNjk5MDg5NTI4LCJpYXQiOjE2Njc1NTM1MjgsImlzcyI6ImFtaW5pdmFuIn0.A1srn810rwLwLeoaUl1zJaoTcy5noFB8Gs10hY_cGDc")
            }
        } returns responseRetrive
        val result = service.getAllArticle("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyaWQiOiI1IiwiZXhwIjoxNjk5MDg5NTI4LCJpYXQiOjE2Njc1NTM1MjgsImlzcyI6ImFtaW5pdmFuIn0.A1srn810rwLwLeoaUl1zJaoTcy5noFB8Gs10hY_cGDc")


        //verify, kita memastikan bahwa metode service.getAllSources() benar-benar dipanggil dengan argumen yang sesuai.

        verify {
            runBlocking {
                service.getAllArticle("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyaWQiOiI1IiwiZXhwIjoxNjk5MDg5NTI4LCJpYXQiOjE2Njc1NTM1MjgsImlzcyI6ImFtaW5pdmFuIn0.A1srn810rwLwLeoaUl1zJaoTcy5noFB8Gs10hY_cGDc")
            }
        }
        assertEquals(result,responseRetrive)

        //assertEquals, kita membandingkan nilai result yang diperoleh dari pemanggilan service.getAllSources()
        // dengan objek palsu responseRetrive, untuk memastikan bahwa hasilnya sesuai dengan yang diharapkan.

    }
}