package br.senai.sp.jandira.s_book.service


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val baseurl = "http://10.0.0.152:8080"

//    private const val baseurl = "http://192.168.0.108:8080"

    private val retrofitFactory =
        Retrofit.Builder().
        baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getLoginService(): LoginService {
        return retrofitFactory.create(LoginService::class.java)
    }

    fun postCadastroService(): CadastroService {
        return retrofitFactory.create(CadastroService::class.java)
    }

    fun postResetPasswordService(): ResetPasswordService {
        return retrofitFactory.create(ResetPasswordService::class.java)
    }

    fun userCategoryService(): UserCategoryService{
        return  retrofitFactory.create(UserCategoryService::class.java)
    }

    fun getCategoryService(): CategoryService{
        return  retrofitFactory.create(CategoryService::class.java)
    }

    fun putChangePassword(): ChangePasswordService{
        return  retrofitFactory.create(ChangePasswordService::class.java)
    }

    fun getAnunciosFavoritadosService(): AnunciosFavoritadosService{
        return  retrofitFactory.create(AnunciosFavoritadosService::class.java)
    }

    fun getUserByIdService(): UserService{
        return  retrofitFactory.create(UserService::class.java)
    }

    fun getAnunciosService(): AnuncisosFeedService{
        return  retrofitFactory.create(AnuncisosFeedService::class.java)
    }

    fun getAnunciosByIdUserService(): AnuncioUserService{
        return  retrofitFactory.create(AnuncioUserService::class.java)
    }

    fun postCadastroAnuncioService(): CadastroAnuncioService {
        return retrofitFactory.create(CadastroAnuncioService::class.java)
    }

    fun getIdiomasService(): IdiomaService{
        return retrofitFactory.create(IdiomaService::class.java)
    }

    fun getEditorasService(): EditoraService{
        return retrofitFactory.create(EditoraService::class.java)
    }

    fun getAutoresService(): AutorService{
        return retrofitFactory.create(AutorService::class.java)
    }

    fun getEstadoLivroService(): EstadoLivroService{
        return retrofitFactory.create(EstadoLivroService::class.java)
    }

    fun getTipoAnuncioService(): TipoAnuncioService{
        return retrofitFactory.create(TipoAnuncioService::class.java)
    }

}