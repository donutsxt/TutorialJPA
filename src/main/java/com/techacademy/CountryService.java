package com.techacademy;

import java.util.List;
import java.util.Optional; // 追加

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CountryService {
    private final CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    // 全件を検索して返す
    public List<Country> getCountryList() {
        // リポジトリのfindAllメソッドを呼び出す
        return repository.findAll();
        }

    //ここから追加
    //1件を検索（検索キーはcode）
    public Country getCountry(String code){//1件だけ検索なので戻り値はシンプルに[Country]だけ、上の全件検索は戻り値がリストなので[List<Country>]
     // findByIdで検索
        Optional<Country> option = repository.findById(code);
        //取得できなかった場合はnullを返す
        Country country = option.orElse(null);
        return country;
    }

    //更新を行う
    @Transactional
    public void updateCountry(String code, String name, int population) {
        Country country = new Country(code, name, population);
        repository.save(country);
    }

    //削除を行う
    @Transactional
    public void deleteCountry(String code) {
        repository.deleteById(code);
    }
}