package vn.edu.iuh.fit.rayarkshop.models;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_photo")
public class ProductPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_photo_id")
    @EqualsAndHashCode.Include
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_variation_id")
    private ProductVariation productVariation;

    @Column(name = "thumbnail_photo_file_name", length = 60)
    private String thumbNailPhotoFileName;

    @Column(name = "thumbnail_photo", columnDefinition = "text")
    private String thumbNailPhoto;

    @Column(name = "large_photo_file_name", length = 60)
    private String largePhotoFileName;

    @Column(name = "large_photo", columnDefinition = "text")
    private String largePhoto;

}
