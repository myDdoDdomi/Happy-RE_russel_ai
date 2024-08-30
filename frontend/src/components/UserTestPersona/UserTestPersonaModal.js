import React, { useEffect, useState } from 'react';
import './UserTestPersonaModal.css'
import '../Button/Button'
import artist from '../../assets/characters/art.png'
import butler from '../../assets/characters/butler.png'
import defaultPersona from '../../assets/characters/default.png'
import general from '../../assets/characters/soldier.png'
import philosopher from '../../assets/characters/steel.png'
import Swal from 'sweetalert2'
import { exp } from '@tensorflow/tfjs';

const happyReList = [defaultPersona, general, butler, philosopher, artist]
const personaList = [
    {},
    {
        name:'해파린 장군',
        description:'안녕하신가, 전사여. 오늘 그대가 치룬 전설적인 전투에 대해 말해주게!',
        imgSrc: happyReList[1]
    },
    {
        name:'해파스찬',
        description:'안녕하세요, 주인님!\
        오늘 주인님을 스트레스 받게 한 일은 없었나요?\
        스트레스 관리를 위해 효율적인 방법을 찾아볼게요.',
        imgSrc:happyReList[2]
    },
    {
        name:'해파라테스',
        description:'안녕하신가. 오늘 하루는 어떠했나.\
        삶의 의미에 대해 생각하기 좋은 날이었나?\
        어떤 생각들이 그대의 마음을 채우고 있는지 궁금하네.',
        imgSrc:happyReList[3]
    },
    {
        name:'셰익스피리',
        description:'안녕하신가! 그대의 이야기를 들려주게나.\
        그대야말로 무대의 주인이자 이야기를 써내려가는 깃펜일지니!',
        imgSrc:happyReList[4]
    }
]

const UserTestPersonaModal = (personaNumber) => {
    const persona = personaList[personaNumber]

    if (persona) {
        Swal.fire({
            background:'#292929',
            icon:'success',
            html: `
                <div style="
                    display: flex;
                    flex-direction: column;
                    justify-content: center;
                    align-items: center;
                    min-width: 100px;
                    font-family: Pretendard;
                    color: white;
                ">
                    <div style="
                        font-size: 18px; /* 폰트 사이즈를 키웠습니다. */
                        margin-bottom: 10px;
                        ">
                        당신에게 어울리는 해파리에요!
                    </div>

                    <img
                        alt="${persona.name}"
                        src="${persona.imgSrc}"
                        style="
                            width: 250px;
                            height: 250px;
                            object-fit: cover;
                            padding: 20px;
                            cursor: pointer;
                        "
                    />
                    <div style="
                    color: white;
                    ">
                    <div style="
                        font-weight: 700;
                        padding-bottom: 5px;
                        font-size: 20px;
                    ">
                        ${persona.name}
                    </div>
                    <div style="
                        font-size: 15px;
                    ">
                        ${persona.description}
                    </div>
                    </div>
                </div>`,
            showCancelButton: false,
            confirmButtonColor: '#3085d6',
            confirmButtonText: '확인',

        })

    }
}

export default UserTestPersonaModal