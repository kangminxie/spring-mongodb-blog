import React, { useState } from 'react';
import { Carousel } from 'react-bootstrap';
import 'holderjs';

const slidesParams = [
  {
    key: 's-01',
    caption: 'First slide label',
    imgUrl: 'holder.js/800x600?text=First slide&bg=373940',
    alt: 'first slide',
    text: 'Paragraph for the first slide',
  },
  {
    key: 's-02',
    caption: 'Second slide label',
    imgUrl: 'holder.js/800x600?text=Second slide&bg=282c34',
    alt: 'second slide',
    text: 'Paragraph for the second slide',
  },
  {
    key: 's-03',
    caption: 'Third slide label',
    imgUrl: 'holder.js/800x600?text=Third slide&bg=20232a',
    alt: 'third slide',
    text: 'Paragraph for the third slide',
  },
];

export const ControlledCarousel: React.FC = () => {
  const [index, setIndex] = useState(0);

  const handleSelect = (selectedIndex: number) => {
    setIndex(selectedIndex);
  };

  return (
    <Carousel activeIndex={index} onSelect={handleSelect}>
      {slidesParams.map((each) => {
        return (
          <Carousel.Item key={each.key}>
            <img
              className='d-block w-100'
              src={`${each.imgUrl}`}
              alt={`${each.alt}`}
            />
            <Carousel.Caption>
              <h3>{each.caption}</h3>
              <p>{each.text}</p>
            </Carousel.Caption>
          </Carousel.Item>
        );
      })}
    </Carousel>
  );
};
