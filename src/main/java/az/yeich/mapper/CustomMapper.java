package az.yeich.mapper;

import az.yeich.dto.CategoryDto;
import az.yeich.dto.MenuDetailDto;
import az.yeich.dto.MenuDto;
import az.yeich.dto.OfferDto;
import az.yeich.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomMapper {

    public List<CategoryDto> categoryListToCategoryDtos(List<Category> categories, String language) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>( categories.size() );
        for ( Category category : categories ) {
            list.add( categoryToCategoryDto( category, language ) );
        }

        return list;
    }

    public List<MenuDto> menuListToMenuDto(List<Menu> menu, String language) {
        if ( menu == null ) {
            return null;
        }

        List<MenuDto> list = new ArrayList<MenuDto>( menu.size() );
        for ( Menu menu1 : menu ) {
            list.add( menuToMenuDto( menu1, language ) );
        }

        return list;
    }

    public List<OfferDto> offerListToOfferDtoList(List<Offer> offers, String language) {
        if ( offers == null ) {
            return null;
        }

        List<OfferDto> list = new ArrayList<OfferDto>( offers.size() );
        for ( Offer offer : offers ) {
            list.add( offerToOfferDto( offer, language ) );
        }

        return list;
    }

    public List<MenuDetailDto> menuDetailListToMenuDetailDtoList(List<MenuDetail> menu, String language) {
        if ( menu == null ) {
            return null;
        }

        List<MenuDetailDto> list = new ArrayList<MenuDetailDto>( menu.size() );
        for ( MenuDetail menuDetail : menu ) {
            list.add( menuDetailToMenuDetailDto( menuDetail, language ) );
        }

        return list;
    }

    protected CategoryDto categoryToCategoryDto(Category category, String language) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(category.getId());

        List<MultiLanguageText> list = category.getTitle();
        if ( list != null ) {
            for ( MultiLanguageText obj : list ) {
                if(obj.getLanguage().equals(language)) {
                    categoryDto.setTitle( obj.getText() );
                    break;
                }
            }
        }
        categoryDto.setImage( category.getImage() );

        categoryDto.setRestaurantCount(category.getRestaurants().size());

        return categoryDto;
    }


    protected MenuDto menuToMenuDto(Menu menu, String language) {
        if ( menu == null ) {
            return null;
        }

        MenuDto menuDto = new MenuDto();

        menuDto.setId(menu.getId());

        List<MultiLanguageText> list = menu.getTitle();
        if ( list != null ) {
            for ( MultiLanguageText obj : list ) {
                if(obj.getLanguage().equals(language)) {
                    menuDto.setTitle( obj.getText() );
                    break;
                }
            }
        }
        menuDto.setImage( menu.getImage() );

        return menuDto;
    }

    protected MenuDetailDto menuDetailToMenuDetailDto(MenuDetail menuDetail, String language) {
        if ( menuDetail == null ) {
            return null;
        }

        MenuDetailDto menuDetailDto = new MenuDetailDto();

        List<MultiLanguageText> list = menuDetail.getTitle();
        if ( list != null ) {
            for ( MultiLanguageText obj : list ) {
                if(obj.getLanguage().equals(language)) {
                    menuDetailDto.setTitle( obj.getText() );
                    break;
                }
            }
        }

        List<MultiLanguageText> list1 = menuDetail.getDescription();
        if ( list1 != null ) {
            for ( MultiLanguageText obj1 : list1 ) {
                if(obj1.getLanguage().equals(language)) {
                    menuDetailDto.setDescription( obj1.getText() );
                    break;
                }
            }
        }

        menuDetailDto.setPrice( menuDetail.getPrice() );
        menuDetailDto.setImage( menuDetail.getImage() );

        return menuDetailDto;
    }



    protected OfferDto offerToOfferDto(Offer offer, String language) {
        if ( offer == null ) {
            return null;
        }

        OfferDto offerDto = new OfferDto();

        List<MultiLanguageText> list = offer.getDesciption();
        if ( list != null ) {
            for ( MultiLanguageText obj : list ) {
                if(obj.getLanguage().equals(language)) {
                    offerDto.setDesciption( obj.getText() );
                    break;
                }
            }
        }

        List<MultiLanguageText> list1 = offer.getSubDescription();
        if ( list != null ) {
            for ( MultiLanguageText obj1 : list1 ) {
                if(obj1.getLanguage().equals(language)) {
                    offerDto.setSubDescription( obj1.getText() );
                    break;
                }
            }
        }

        offerDto.setOfferHours(offer.getOfferHours());

        offerDto.setImage( offer.getImage() );

        offerDto.setRestaurantName( offer.getRestaurantName() );

        offerDto.setRestaurantId( offer.getRestaurantId() );

        return offerDto;
    }
}
