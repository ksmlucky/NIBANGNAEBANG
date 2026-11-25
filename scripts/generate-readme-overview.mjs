import fs from 'node:fs/promises';
import path from 'node:path';
import {fileURLToPath} from 'node:url';
const e=v=>String(v).replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&apos;'}[c]));
function art(kind,stage,c){
 const line=`fill="none" stroke="${c}" stroke-width="3" stroke-linecap="round" stroke-linejoin="round"`;
 const rect=(x,y,w,h,r=8)=>`<rect x="${x}" y="${y}" width="${w}" height="${h}" rx="${r}" ${line}/>`;
 const path=d=>`<path d="${d}" ${line}/>`;
 const dot=(x,y,r=7)=>`<circle cx="${x}" cy="${y}" r="${r}" fill="${c}"/>`;
 const bars=()=>[80,58,100].map((w,i)=>`<rect x="74" y="${22+i*25}" width="${w}" height="12" rx="6" fill="${c}" opacity="${1-i*.2}"/>`).join('');
 const persons=()=>[58,119,180].map((x,i)=>`<circle cx="${x}" cy="30" r="13" ${line}/>${path(`M${x-20} 79v-9a20 20 0 0 1 40 0v9`)}`).join('');
 const calendar=()=>rect(65,8,112,90)+path('M65 32H177 M91 1v15 M151 1v15')+[91,121,151].map(x=>dot(x,53,5)+dot(x,78,5)).join('');
 const network=()=>path('M49 57L120 20L190 57L120 94Z M49 57H190 M120 20V94')+[[49,57],[120,20],[190,57],[120,94]].map(([x,y])=>`<circle cx="${x}" cy="${y}" r="12" fill="white" stroke="${c}" stroke-width="3"/>`).join('');
 const file=()=>rect(78,3,83,99)+path('M95 26H145 M95 44H138 M95 62H145 M95 80H125');
 const pin=()=>path('M120 99S82 59 82 37a38 38 0 0 1 76 0c0 22-38 62-38 62Z')+`<circle cx="120" cy="36" r="12" ${line}/>`;
 const chat=()=>rect(49,8,111,65)+path('M65 73v19l22-19 M177 34h14v62h-28l-16 14v-14h-26 M69 30h71 M69 48h45');
 const house=()=>path('M56 48L120 0L184 48 M69 39V99h102V39 M106 99V66h28v33')+rect(91,39,15,15,1)+rect(135,39,15,15,1);
 const sequence=()=>[0,1,2,3].map((row)=>[0,1,2,3,4,5,6].map((col)=>`<rect x="${32+col*26}" y="${row*25}" width="20" height="17" rx="4" fill="${c}" opacity="${[.25,.5,.75,1][(row+col)%4]}"/>`).join('')).join('');
 const tree=()=>path('M41 52H83V19H142 M83 52V86H142 M142 19V5H187 M142 19V36H187 M142 86V68H187 M142 86V102H187')+[[191,5],[191,36],[191,68],[191,102]].map(([x,y])=>dot(x,y,5)).join('');
 const video=()=>rect(30,7,172,93)+rect(43,20,68,61)+rect(123,20,66,27)+rect(123,57,66,24)+`<path d="M68 34l25 17-25 16Z" fill="${c}"/>`;
 const check=()=>rect(77,2,92,101)+path('M94 24l5 5 10-12 M119 23h32 M94 48l5 5 10-12 M119 47h32 M94 72l5 5 10-12 M119 71h32');
 const cards=()=>[0,1,2].map((n)=>`<g transform="translate(${40+n*57},${8+(n%2)*10})">${rect(0,0,48,80,6)}<circle cx="24" cy="24" r="10" fill="${c}" opacity=".4"/>${path('M11 48h26 M11 62h18')}</g>`).join('');
 const pitch=()=>rect(39,0,164,107,2)+path('M121 0V107 M39 33h24v40H39 M203 33h-24v40h24')+`<circle cx="121" cy="53" r="19" ${line}/>`+[[63,53],[96,22],[96,84],[147,29],[147,77],[180,53]].map(([x,y])=>dot(x,y,5)).join('');
 const score=()=>rect(43,10,155,88)+path('M122 27v55 M80 49h9 M153 49h9')+`<text x="72" y="70" font-size="37" font-weight="700" fill="${c}">3</text><text x="148" y="70" font-size="37" font-weight="700" fill="${c}">2</text>`;
 const designs={people:[persons,network,calendar],home:[house,pin,chat],sequence:[file,sequence,tree],classroom:[calendar,video,check],game:[cards,bars,cards],dex:[cards,check,network],football:[persons,pitch,score]};
 return designs[kind][stage]();
}
export function overviewSvg(p){
 return `<svg xmlns="http://www.w3.org/2000/svg" width="1200" height="720" viewBox="0 0 1200 720" role="img" aria-labelledby="title desc"><title id="title">${e(p.name)} 기능 요약</title><desc id="desc">${e(p.summary)} ${e(p.stages.map(s=>s.join(': ')).join(' → '))} 설명용 도식입니다.</desc><rect width="1200" height="720" rx="24" fill="${p.bg}"/><g font-family="Malgun Gothic, Apple SD Gothic Neo, Arial, sans-serif" fill="${p.ink}"><rect x="52" y="47" width="7" height="25" rx="3" fill="${p.accent}"/><text x="74" y="67" font-size="21" font-weight="600" fill="${p.accent}">${e(p.category)}</text><text x="52" y="148" font-size="${p.kind==='sequence'?52:58}" font-weight="800" letter-spacing="-2">${e(p.name)}</text><text x="54" y="203" font-size="28" letter-spacing="-.6">${e(p.tagline)}</text><text x="54" y="257" font-size="17" letter-spacing="3" fill="${p.accent}">HOW IT WORKS</text>${p.stages.map((s,i)=>{const x=52+i*370;return `<g transform="translate(${x},280)"><rect width="356" height="315" rx="20" fill="white"/><rect x="20" y="20" width="39" height="29" rx="8" fill="${p.soft}"/><text x="39.5" y="41" text-anchor="middle" font-size="16" font-weight="700" fill="${p.accent}">0${i+1}</text><g transform="translate(60,51)">${art(p.kind,i,p.accent)}</g><text x="28" y="207" font-size="30" font-weight="700" letter-spacing="-1">${e(s[0])}</text><text x="28" y="248" font-size="23">${e(s[1])}</text><text x="28" y="281" font-size="23" fill="${p.accent}">${e(s[2])}</text></g>`;}).join('')}<line x1="54" y1="629" x2="1146" y2="629" stroke="${p.accent}" opacity=".2"/><text x="54" y="668" font-size="20" fill="${p.accent}">${e(p.note)}</text><text x="1145" y="701" text-anchor="end" font-size="12" letter-spacing="1.4" opacity=".65">FEATURE OVERVIEW · NOT A LIVE SCREENSHOT</text></g></svg>`;
}
// Portable source for each repository. No package installation is needed for SVG.
if(process.argv[1]&&path.resolve(process.argv[1])===fileURLToPath(import.meta.url)){
 const root=path.resolve(path.dirname(fileURLToPath(import.meta.url)),'..');
 const source=path.join(root,'docs/diagrams/readme-overview.json');
 const config=JSON.parse(await fs.readFile(source,'utf8'));
 await fs.writeFile(path.join(root,'docs/diagrams/readme-overview.svg'),overviewSvg(config));
 console.log('Updated docs/diagrams/readme-overview.svg. Re-export the PNG before publishing a changed diagram.');
}
